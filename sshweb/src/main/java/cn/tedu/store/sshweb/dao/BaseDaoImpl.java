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
 * BaseDaoImpl:操作数据库的基本方法代码
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
	 * 获取数据连接回话的方法
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
	 * 用hql语句查询多条记录，没有分页，lsit返回
	 * @param hql
	 * @param objs  替换hql语句中？占位符的实参
	 * @param alias   替换hql语句中:name占位符的实参
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Object[] objs, Map<String, Object> alias) {
		hql = initSort(hql);//初始化排序规则
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql里？占位符替换
		setAliasParameter(query,alias);
		return query.list();
	}

	/**
	 *  替换hql语句中:name占位符的实参
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
	 * 将hql里？占位符替换
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
	 * 给hql语句加排序规则
	 * @param hql
	 * @return
	 */
	private String initSort(String hql) {
		String sort = SystemContext.getSort(); // 数据我们可以在Controller放
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
	 * 查询多条数据，支持分页
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pager<T> find(String hql, Object[] objs, Map<String, Object> alias){
		hql = initSort(hql);//初始化排序规则
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql里？占位符替换
		setAliasParameter(query,alias);
		Pager<T> pager = new Pager<T>();
		setPager(query,pager);
		List<T> datas = query.list();
		pager.setRows(datas);
		
		//还需要一个查询的中条数   select count(*) from  users where id>10
		String countHql = getCountHql(hql);
		Query countQuery = getSession().createQuery(countHql);
		setParameter(countQuery,objs);//将hql里？占位符替换
		setAliasParameter(countQuery,alias);
		long total = (Long)countQuery.uniqueResult();
		pager.setTotal((int) total);
		
		return pager;
		
	}

	private String getCountHql(String hql) {
		String hhql = hql.substring(hql.indexOf("from")); // 拿到hql语句的form开始后半部分：from  users where id>10
		String countHql = "select count(*) " + hhql;
		//hql语句，fetch
		countHql = countHql.replace("fetch", "");
		return countHql;
	}

	private void setPager(Query<T> query, Pager<T> pager) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset == null || pageOffset < 0) {
			pageOffset = 0; // pageOffset的默认值
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;  // 没有设置每页大小，默认每页显示10条
		}
		pager.setOffset(pageOffset);
		pager.setSize(pageSize);
		// data数据，也就是分页的数据  select id,username,pasword from  users where id>10; 一张页面上显示的数据
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
		
	}
	
	//使用sql语句查询的方法，list，pager
	
	/**
	 * 针对一些特殊的查询，返回不受泛型的制约，返回object
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object queryByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql里？占位符替换
		setAliasParameter(query,alias);
		return query.uniqueResult();
	}
	
	/**
	 * 应对某些特殊情况要用hql语句，来做更新
	 * @param hql
	 * @param objs
	 * @param alias
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql里？占位符替换
		setAliasParameter(query,alias);
		query.executeUpdate();
	}

	
}







