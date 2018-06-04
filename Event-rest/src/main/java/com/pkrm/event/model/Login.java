package com.pkrm.event.model;

public class Login {
	private String userName;
	private String passWord;
	private int roleId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", passWord=" + passWord + ", roleId=" + roleId + "]";
	}
	
}
