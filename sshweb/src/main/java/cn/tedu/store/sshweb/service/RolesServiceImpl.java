package cn.tedu.store.sshweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.sshweb.dao.RolesDao;
import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;

@Service("rolesService")
public class RolesServiceImpl extends BaseServiceImpl<Role> implements RolesService{

	@Autowired
	private RolesDao rolesDao;
	
	public List<Role> getAllRole() {
		
		return rolesDao.getAllRole();
	}

	public Pager<Role> getAllLimtPermission() {
		
		return rolesDao.getAllLimtPermission();
	}

}
