package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;

public interface PermissionService extends BaseService<Permission>{

	//��ѯ���н�ɫ��Ϣ
	public List<Permission> getPermissionDao();
	
	//��ѯ��ɫȨ��������Ϣ֧�ַ�ҳ��ѯ
	public Pager<Permission> getAllLimtPermission();
	
	public void initPermission(List<String> resources);
}
