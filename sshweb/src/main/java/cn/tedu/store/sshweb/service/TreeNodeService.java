package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.TreeNode;

public interface TreeNodeService extends BaseService<TreeNode>{

	//��ȡ���еĽڵ�
	public List<TreeNode> getAllNode();
	
	//��ȥ����ID
	public int getNodeId();
	
}
