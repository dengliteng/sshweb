package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;


public interface RolesDao extends BaseDao<Role>{
	
	//��ȡ���еĽ�ɫ��Ϣ
	public List<Role> getAllRole();
	
	//֧�ַ�ҳ��ѯ
	public Pager<Role> getAllLimtPermission();
	
}
