package cn.tedu.store.sshweb.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private int id;
	private String roleName;
	private Integer state;
	
	private Set<Permission> Permissions = new HashSet<Permission>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Set<Permission> getPermissions() {
		return Permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		Permissions = permissions;
	}
	
	
}
