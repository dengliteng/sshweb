package cn.tedu.store.sshweb.dao;


import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.SystemContext;

/**
 * BaseDaoImpl:�������ݿ�Ļ�����������
 * @author deng
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;

	private Class<?> clazz;

	public Class<?> getClazz() {
		if (clazz == null) {
			clazz = ((Class<?>) ((((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments())[0]));
		}
		return clazz;

	}

	/**
	 * ��ȡ�������ӻػ��ķ���
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T add(T t) {
		getSession().save(t);
		return t;
	}

	public void delete(int id) {
		getSession().delete(load(id));
	}

	public void update(T t) {
		getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	public T load(int id) {
		return (T) getSession().load(getClazz(), id);
	}

	/**
	 * ��hql����ѯ������¼��û�з�ҳ��lsit����
	 * @param hql
	 * @param objs  �滻hql����У�ռλ����ʵ��
	 * @param alias   �滻hql�����:nameռλ����ʵ��
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Object[] objs, Map<String, Object> alias) {
		hql = initSort(hql);//��ʼ���������
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//��hql�ռλ���滻
		setAliasParameter(query,alias);
		return query.list();
	}

	/**
	 *  �滻hql�����:nameռλ����ʵ��
	 * @param query
	 * @param alias
	 */
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query<T> query, Map<String, Object> alias) {
		if(alias != null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					query.setParameterList(key, (Collection)val);
				}else {
					query.setParameter(key, val);
				}
			}
		}
	}

	/**
	 * ��hql�ռλ���滻
	 * @param query
	 * @param objs
	 */
	private void setParameter(Query<T> query,Object[] objs) {
		if(objs != null && objs.length > 0) {
			int index = 0;
			for(Object obj:objs) {
				query.setParameter(index++, obj);
			}
		}
	}

	/**
	 * ��hql�����������
	 * @param hql
	 * @return
	 */
	private String initSort(String hql) {
		String sort = SystemContext.getSort(); // �������ǿ�����Controller��
		String order = SystemContext.getOrder();
		if (sort != null && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (!"desc".equals(order)) {
				hql += " asc";
			} else {
				hql += " desc";
			}
		}
		return hql;
	}
	
	/**
	 * ��ѯ�������ݣ�֧�ַ�ҳ
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pager<T> find(String hql, Object[] objs, Map<String, Object> alias){
		hql = initSort(hql);//��ʼ���������
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//��hql�ռλ���滻
		setAliasParameter(query,alias);
		Pager<T> pager = new Pager<T>();
		setPager(query,pager);
		List<T> datas = query.list();
		pager.setRows(datas);
		
		//����Ҫһ����ѯ��������   select count(*) from  users where id>10
		String countHql = getCountHql(hql);
		Query countQuery = getSession().createQuery(countHql);
		setParameter(countQuery,objs);//��hql�ռλ���滻
		setAliasParameter(countQuery,alias);
		long total = (Long)countQuery.uniqueResult();
		pager.setTotal((int) total);
		
		return pager;
		
	}

	private String getCountHql(String hql) {
		String hhql = hql.substring(hql.indexOf("from")); // �õ�hql����form��ʼ��벿�֣�from  users where id>10
		String countHql = "select count(*) " + hhql;
		//hql��䣬fetch
		countHql = countHql.replace("fetch", "");
		return countHql;
	}

	private void setPager(Query<T> query, Pager<T> pager) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset == null || pageOffset < 0) {
			pageOffset = 0; // pageOffset��Ĭ��ֵ
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;  // û������ÿҳ��С��Ĭ��ÿҳ��ʾ10��
		}
		pager.setOffset(pageOffset);
		pager.setSize(pageSize);
		// data���ݣ�Ҳ���Ƿ�ҳ������  select id,username,pasword from  users where id>10; һ��ҳ������ʾ������
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
		
	}
	
	//ʹ��sql����ѯ�ķ�����list��pager
	
	/**
	 * ���һЩ����Ĳ�ѯ�����ز��ܷ��͵���Լ������object
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object queryByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//��hql�ռλ���滻
		setAliasParameter(query,alias);
		return query.uniqueResult();
	}
	
	/**
	 * Ӧ��ĳЩ�������Ҫ��hql��䣬��������
	 * @param hql
	 * @param objs
	 * @param alias
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//��hql�ռλ���滻
		setAliasParameter(query,alias);
		query.executeUpdate();
	}

	
}







