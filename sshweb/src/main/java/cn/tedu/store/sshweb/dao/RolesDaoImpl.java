package cn.tedu.store.sshweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;

@Repository("rolesDao")
public class RolesDaoImpl extends BaseDaoImpl<Role> implements RolesDao{

	public List<Role> getAllRole() {
		String hql = "from Role";
		return super.list(hql, null, null);
	}

	public Pager<Role> getAllLimtPermission() {
		String hql = "from Role";
		return super.find(hql, null, null);
	}

}
