package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;

public interface UserDao extends BaseDao<User>{

	//查询用户所有信息
	public List<User> getAllUser();
	
	//Easyui查询用户所有信息支持分页查询
	public Pager<User> getAllLimt(User user);
	
	//查询用户所有信息支持分页查询
	public Pager<User> getAllLimtUser();

	//获取返回的对象
	public User login(String username);
}
