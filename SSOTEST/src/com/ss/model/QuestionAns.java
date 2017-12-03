package com.ss.model;

public class QuestionAns {
private int id;
private String question,
			   ansOne,ansTwo,ansThree,
			   ansFour,optOne,optTwo,
			   optThree,optFour,skill;
public QuestionAns() {}
public QuestionAns(String question,String ansOne, String ansTwo, String ansThree,
		           String ansFour, String optOne, String optTwo, String optThree,
		           String optFour,String skill) {
	super();
	this.question=question;
	this.ansOne=ansOne;
	this.ansTwo=ansTwo;
	this.ansThree=ansThree;
	this.ansFour=ansFour;
	this.optOne=optOne;
	this.optTwo=optTwo;
	this.optThree=optThree;
	this.optFour=optFour;
	this.skill=skill;
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getAnsOne() {
	return ansOne;
}
public void setAnsOne(String ansOne) {
	this.ansOne = ansOne;
}
public String getAnsTwo() {
	return ansTwo;
}
public void setAnsTwo(String ansTwo) {
	this.ansTwo = ansTwo;
}
public String getAnsThree() {
	return ansThree;
}
public void setAnsThree(String ansThree) {
	this.ansThree = ansThree;
}
public String getAnsFour() {
	return ansFour;
}
public void setAnsFour(String ansFour) {
	this.ansFour = ansFour;
}
public String getOptOne() {
	return optOne;
}
public void setOptOne(String optOne) {
	this.optOne = optOne;
}
public String getOptTwo() {
	return optTwo;
}
public void setOptTwo(String optTwo) {
	this.optTwo = optTwo;
}
public String getOptThree() {
	return optThree;
}
public void setOptThree(String optThree) {
	this.optThree = optThree;
}
public String getOptFour() {
	return optFour;
}
public void setOptFour(String optFour) {
	this.optFour = optFour;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}

}
