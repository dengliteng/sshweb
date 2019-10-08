package cn.tedu.store.sshweb.model;

public class ComboBox {

	private int id;
	private String text;
	
	public ComboBox() {
		super();
	}
	
	public ComboBox(int id, String text) {
		super();
		this.id = id;
		this.text = text;
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
	@Override
	public String toString() {
		return "ComboBox [id=" + id + ", text=" + text + "]";
	}
	
}
