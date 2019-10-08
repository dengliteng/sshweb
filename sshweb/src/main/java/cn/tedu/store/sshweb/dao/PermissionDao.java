package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;
import cn.tedu.store.sshweb.model.User;

public interface PermissionDao extends BaseDao<Permission>{

	//获取所有的权限
	public List<Permission> getPermission();
	
	//查询用户所有信息支持分页查询
		public Pager<Permission> getAllLimtPermission();

		public boolean isExitResource(String resource);
}
