package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.User;

public interface UserService extends BaseService<User>{

	//��ѯ�û�������Ϣ
	public List<User> getAllUser();
	
	//��ѯ�û�������Ϣ֧�ַ�ҳ��ѯ
	public Pager<User> getAllLimt(User user);
	
	public Pager<User> getAllLimtUser();
	
	//�޸��û� ��������������ʾ
	public void UpdataUser(User user);

	//��ȡ��¼�Ķ���
	public User login(String username, String password);

	
};
