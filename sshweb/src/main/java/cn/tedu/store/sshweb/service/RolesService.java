package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;

public interface RolesService extends BaseService<Role>{

	public List<Role> getAllRole();
	
	public Pager<Role> getAllLimtPermission();
}
