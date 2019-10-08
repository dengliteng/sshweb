package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;

public interface UserDao extends BaseDao<User>{

	//��ѯ�û�������Ϣ
	public List<User> getAllUser();
	
	//Easyui��ѯ�û�������Ϣ֧�ַ�ҳ��ѯ
	public Pager<User> getAllLimt(User user);
	
	//��ѯ�û�������Ϣ֧�ַ�ҳ��ѯ
	public Pager<User> getAllLimtUser();

	//��ȡ���صĶ���
	public User login(String username);
}
