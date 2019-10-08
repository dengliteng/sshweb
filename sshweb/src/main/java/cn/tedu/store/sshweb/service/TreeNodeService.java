package cn.tedu.store.sshweb.service;

import java.util.List;

import cn.tedu.store.sshweb.model.TreeNode;

public interface TreeNodeService extends BaseService<TreeNode>{

	//获取所有的节点
	public List<TreeNode> getAllNode();
	
	//获去最大的ID
	public int getNodeId();
	
}
