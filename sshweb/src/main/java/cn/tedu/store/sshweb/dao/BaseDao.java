package cn.tedu.store.sshweb.dao;
/**
 * Dao:�������ݿ�
 * @author deng
 *
 */
public interface BaseDao<T> {
	//BaseDao:�Ѳ������ݿ�ĵ�������Ĺ����Դ���,���븴��,����,����,ͨ����
	//�Ѳ������ݿ��������Ĺ����Դ���,��ɾ�Ĳ�
	
	/**
	 * ���
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id);
	/**
	 * �޸�
	 * @param t
	 */
	public void update(T t);
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public T load(int id);
}







