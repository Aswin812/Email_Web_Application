package com.emailApplication.dto;


public class AccountsDTO {

	private String userName;
	private String password;
	private int age;
	private String gender;
	
	

	public AccountsDTO(String name, String password, int age, String gender) {
		userName=name;
		this.password=password;
		this.age=age;
		this.gender=gender;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}

