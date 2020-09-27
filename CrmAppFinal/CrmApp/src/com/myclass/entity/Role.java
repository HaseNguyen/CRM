package com.myclass.entity;

public class Role {
	//thuoc tinh
	private int roleId;
	private String roleName;
	private String roleText;
	
	//get, set
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleText() {
		return roleText;
	}
	public void setRoleText(String roleText) {
		this.roleText = roleText;
	}
	
	//ham khoi tao
	public Role(int roleId, String roleName, String roleText) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleText = roleText;
	}
	
	public Role() {
		
	}
	
}
