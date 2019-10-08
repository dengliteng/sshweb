package cn.tedu.store.sshweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.sshweb.dao.TreeNodeDao;
import cn.tedu.store.sshweb.model.TreeNode;

@Service("treeNodeService")
public class TreeNodeServiceImpl extends BaseServiceImpl<TreeNode> implements TreeNodeService{

	@Autowired
	private TreeNodeDao treeNodeDao;
	
	public List<TreeNode> getAllNode() {
		//获取到所有的节点,nodes所有节点的对象
		List<TreeNode> nodes = treeNodeDao.getAllNode();
		//要做的事情,就是区分出父节点,把对象子节点放入对应的父节点到的Children属性里面
		List<TreeNode> parents = new ArrayList<TreeNode>();//这个集合放没有父节点的根节点
		Map<Integer,TreeNode> map = new HashMap<Integer,TreeNode>();//nodes所有的节点对象,都放进去
		for(TreeNode node : nodes ){
			map.put(node.getId(), node);
			if(node.getParentId()==null){
				parents.add(node);
			}
		}
		
		for(TreeNode node : nodes){
			TreeNode parent = map.get(node.getParentId());
			if(parent!=null){
				parent.getChildren().add(node);
			}
		}
		
		Set<Entry<Integer,TreeNode>> set = map.entrySet();
		for(Entry<Integer,TreeNode> entry:set){
			System.out.println("Map==========="+entry);
		}
		
		return parents;
	}

	public int getNodeId() {
		
		return treeNodeDao.getNodeId();
	}

}
