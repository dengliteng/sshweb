package cn.tedu.store.sshweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{

	public List<Permission> getPermission() {
		String hql = "from Permission";
		return super.list(hql, null, null);
	}

	public Pager<Permission> getAllLimtPermission() {
		String hql = "from Permission";
		return super.find(hql, null, null);
	}

	public boolean isExitResource(String resource) {
		String hql = "select count(*) from Permission p where p.resource=?";
		long count = (Long) super.queryByHql(hql, new Object[]{resource}, null);
		return count>0?true:false;
	}

	

}
