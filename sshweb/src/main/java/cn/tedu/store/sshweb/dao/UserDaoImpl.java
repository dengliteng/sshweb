package cn.tedu.store.sshweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public List<User> getAllUser() {
		String hql = " from User";
		return super.list(hql, null, null);
	}

	public Pager<User> getAllLimt(User user) {
		String hql = "from User u where 1=1";
		if(user.getId()>0) hql+=" and u.id="+user.getId();
		if(!"".equals(user.getUsername())&&user.getUsername()!=null) 
				hql+=" and u.username like '%"+user.getUsername()+"%'";
		return super.find(hql, null, null);
	}

	public Pager<User> getAllLimtUser() {
		String hql = " from User";
		return super.find(hql, null, null);
	}

	public User login(String username) {
		String hql = "select u from User u where u.username=?";
		return (User) super.queryByHql(hql, new Object[]{username}, null);
	}

}
