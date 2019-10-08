package cn.tedu.store.sshweb.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private int id;
	private String text;
	private List<TreeNode> children = new ArrayList<TreeNode>();
	private Integer parentId;
	
	public TreeNode() {
		super();
	}
	
	public TreeNode(int id, String text, List<TreeNode> children, Integer parentId) {
		super();
		this.id = id;
		this.text = text;
		this.children = children;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", children=" + children + ", parentId=" + parentId + "]";
	}
	
	
}
