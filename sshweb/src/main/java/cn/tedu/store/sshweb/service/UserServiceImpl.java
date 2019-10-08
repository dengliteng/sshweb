package cn.tedu.store.sshweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.sshweb.dao.UserDao;
import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;
import cn.tedu.store.sshweb.util.SecurityUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Autowired
	private UserDao userDao;
	
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	public Pager<User> getAllLimt(User user) {
		
		return userDao.getAllLimt(user);
	}

	public Pager<User> getAllLimtUser() {
		
		return userDao.getAllLimtUser();
	}

	public void UpdataUser(User user) {
		int userid = user.getId();
		User userDB = userDao.load(userid);
		userDB.setUsername(user.getUsername());
		userDB.setRegDate(user.getRegDate());
		userDB.setRoles(user.getRoles());
		userDB.setState(user.getState());
		if(user.getPassword()==null || "".equals(user.getPassword())){
			
		}else{
			userDB.setPassword(SecurityUtil.md5(user.getUsername(), user.getPassword()));
		}
		userDao.update(userDB);
	}

	public User login(String username, String password) {
		User user = userDao.login(username);
		if(user==null) throw new RuntimeException("用户名或者密码错误");
		if(!user.getPassword().equals(SecurityUtil.md5(username, password)))
			throw new RuntimeException("用户名或者密码错误");
		if(user.getState()!=1) throw new RuntimeException("该用户已经被禁用了");
		return user;
	}

}
