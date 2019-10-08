package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;
import cn.tedu.store.sshweb.model.User;

public interface PermissionDao extends BaseDao<Permission>{

	//��ȡ���е�Ȩ��
	public List<Permission> getPermission();
	
	//��ѯ�û�������Ϣ֧�ַ�ҳ��ѯ
		public Pager<Permission> getAllLimtPermission();

		public boolean isExitResource(String resource);
}
