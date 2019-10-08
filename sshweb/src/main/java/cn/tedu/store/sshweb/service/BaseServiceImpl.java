package cn.tedu.store.sshweb.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tedu.store.sshweb.dao.BaseDao;

public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	protected BaseDao<T> baseDao;
	
	public T add(T t) {
		return baseDao.add(t);
	}

	public void delete(int id) {
		baseDao.delete(id);
		
	}

	public void update(T t) {
		baseDao.update(t);
		
	}

	public T load(int id) {
		return baseDao.load(id);
	}

}
