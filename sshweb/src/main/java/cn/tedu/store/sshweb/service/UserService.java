package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;

public interface UserService extends BaseService<User>{

	//查询用户所有信息
	public List<User> getAllUser();
	
	//查询用户所有信息支持分页查询
	public Pager<User> getAllLimt(User user);
	
	public Pager<User> getAllLimtUser();
	
	//修改用户 让其密码密文显示
	public void UpdataUser(User user);

	//获取登录的对象
	public User login(String username, String password);

	
};
