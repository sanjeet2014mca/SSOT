package com.ss.model;

public class UserDetails {
private int id;
private String name,password,email,
			   contactno,skill;
public UserDetails() {}
public UserDetails(String name, String password, String email, String contactno,String skill) {
	super();
	this.name = name;
	this.password = password;
	this.email = email;
	this.contactno = contactno;
	this.skill=skill;
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContactno() {
	return contactno;
}
public void setContactno(String contactno) {
	this.contactno = contactno;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}

}
