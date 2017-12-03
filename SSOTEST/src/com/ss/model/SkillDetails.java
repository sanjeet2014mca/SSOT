package com.ss.model;

import java.util.Date;

public class SkillDetails {
	private int id;
	private String skill;
	private  Date dt;
	public SkillDetails() {}
	public SkillDetails(String skill) {
		super();
		this.skill=skill;
		
	}
	
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
}
