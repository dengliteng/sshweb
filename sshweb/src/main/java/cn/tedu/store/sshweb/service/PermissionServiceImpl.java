package cn.tedu.store.sshweb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.sshweb.dao.PermissionDao;
import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;

@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{

	@Autowired
	public PermissionDao permissionDao;
	
	public List<Permission> getPermissionDao() {
		
		return permissionDao.getPermission();
	}

	public Pager<Permission> getAllLimtPermission() {
		
		return permissionDao.getAllLimtPermission();
	}

	public void initPermission(List<String> resources) {
		boolean isExit = false;
		for(String resource : resources){
			isExit = permissionDao.isExitResource(resource);//true´ú±íÓÐ
			if(!isExit){
				Permission permission = new Permission();
				permission.setResource(resource);
				permission.setState(1);
				permissionDao.add(permission);
			}
		}
	}

}
