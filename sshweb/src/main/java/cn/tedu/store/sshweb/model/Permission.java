package cn.tedu.store.sshweb.model;

public class Permission {

	private int id;
	private String resource;
	private Integer state;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", resource=" + resource + ", state=" + state + "]";
	}
	
	
	
}
