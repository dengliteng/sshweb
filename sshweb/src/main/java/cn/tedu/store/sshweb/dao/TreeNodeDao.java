package cn.tedu.store.sshweb.dao;

import java.util.List;

import cn.tedu.store.sshweb.model.TreeNode;

public interface TreeNodeDao extends BaseDao<TreeNode>{

	//��ȡ���еĽڵ�
	public List<TreeNode> getAllNode();
	
	//��ȥ����ID
	public int getNodeId();
}
