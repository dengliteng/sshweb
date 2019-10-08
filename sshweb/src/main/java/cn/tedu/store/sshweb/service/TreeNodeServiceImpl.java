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
		//��ȡ�����еĽڵ�,nodes���нڵ�Ķ���
		List<TreeNode> nodes = treeNodeDao.getAllNode();
		//Ҫ��������,�������ֳ����ڵ�,�Ѷ����ӽڵ�����Ӧ�ĸ��ڵ㵽��Children��������
		List<TreeNode> parents = new ArrayList<TreeNode>();//������Ϸ�û�и��ڵ�ĸ��ڵ�
		Map<Integer,TreeNode> map = new HashMap<Integer,TreeNode>();//nodes���еĽڵ����,���Ž�ȥ
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
