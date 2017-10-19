package com.blog.util.pojo;

public class Ztree {
	
	private Long id;//主键ID
	private String name;//名称
	private Long pId;//父节点ID
	private Boolean open;//是否打开节点。true:是 false:否
	private Boolean checked;//是否选中。true:是 false:否
	
	public Ztree(){
		open = false;
		checked = false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	
}
