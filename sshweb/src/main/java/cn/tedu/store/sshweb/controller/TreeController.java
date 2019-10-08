package cn.tedu.store.sshweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.sshweb.model.TreeNode;
import cn.tedu.store.sshweb.service.TreeNodeService;

@Controller
public class TreeController {

	@Autowired
	private TreeNodeService treeNodeService;
	
	@ResponseBody
	@RequestMapping("/getAllTree")
	public List<TreeNode> getAllTree(){
		List<TreeNode> list = treeNodeService.getAllNode();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/getNewId")
	public int getNewId(){
		return treeNodeService.getNodeId();
	}
	
	@ResponseBody
	@RequestMapping("/addNode")
	public String addNode(TreeNode treenode){
		treeNodeService.add(treenode);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/deleteNode")
	public String deleteNode(@RequestParam("id") int id){
		treeNodeService.delete(id);
		return "success";
	}
	
}
