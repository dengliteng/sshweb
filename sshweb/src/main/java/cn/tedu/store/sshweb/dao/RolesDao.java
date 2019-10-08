package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;


public interface RolesDao extends BaseDao<Role>{
	
	//获取所有的角色信息
	public List<Role> getAllRole();
	
	//支持分页查询
	public Pager<Role> getAllLimtPermission();
	
}
