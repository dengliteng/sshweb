package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;

public interface PermissionService extends BaseService<Permission>{

	//查询所有角色信息
	public List<Permission> getPermissionDao();
	
	//查询角色权限所有信息支持分页查询
	public Pager<Permission> getAllLimtPermission();
	
	public void initPermission(List<String> resources);
}
