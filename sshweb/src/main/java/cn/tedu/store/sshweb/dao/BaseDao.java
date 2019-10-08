package cn.tedu.store.sshweb.dao;
/**
 * Dao:操作数据库
 * @author deng
 *
 */
public interface BaseDao<T> {
	//BaseDao:把操作数据库的的最基本的功能性代码,代码复用,代码,方法,通用性
	//把操作数据库的最基本的功能性代码,增删改查
	
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t);
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public T load(int id);
}







