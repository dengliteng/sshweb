package cn.tedu.store.sshweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.store.sshweb.model.TreeNode;

@Repository("treeNodeDao")
public class TreeNodeDaoImpl extends BaseDaoImpl<TreeNode> implements TreeNodeDao{

	public List<TreeNode> getAllNode() {
		String hql = "from TreeNode";
		return super.list(hql, null, null);
	}

	public int getNodeId() {
		String hql = "select max(id) from TreeNode";
		return Integer.parseInt(super.queryByHql(hql, null, null).toString());
	}

}
