package com.wp.auntweb.DTO;

import java.sql.Timestamp;

public class QuestionDTO {
	
	//Question
    public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public Timestamp getSavedate() {
		return savedate;
	}
	public void setSavedate(Timestamp savedate) {
		this.savedate = savedate;
	}
	public Timestamp getModifydate() {
		return modifydate;
	}
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}
	
	int num;
    String title;
    String content;
    String user;
    String access;
    Timestamp savedate;
    Timestamp modifydate;
    public QuestionDTO() {
    	
    }
    
    public QuestionDTO(int num, String title, String content, String user, String access, Timestamp savedate, Timestamp modifydate) {
    	this.num = num;
    	this.title = title;
    	this.content = content;
    	this.user = user;
    	this.access = access;
    	this.savedate = savedate;
    	this.modifydate = modifydate; 
    }
    
    //Answer 
    
    int num2;
    public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getAccess2() {
		return access2;
	}
	public void setAccess2(String access2) {
		this.access2 = access2;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public Timestamp getSavedate2() {
		return savedate2;
	}
	public void setSavedate2(Timestamp savedate2) {
		this.savedate2 = savedate2;
	}
	public Timestamp getModifydate2() {
		return modifydate2;
	}
	public void setModifydate2(Timestamp modifydate2) {
		this.modifydate2 = modifydate2;
	}

	int q_num;
    String title2;
    String content2;
    String access2;
    String user2; 
    Timestamp savedate2;
    Timestamp modifydate2; 
    
    public QuestionDTO(int num2, int q_num, String title2, String content2, String access2, String user2, Timestamp savedate2, Timestamp modifydate2) {
    	this.num2 = num2;
    	this.q_num = q_num;
    	this.title2 = title2;
    	this.content2 = content2;
    	this.access2 = access2;
    	this.user2 = user2;
    	this.savedate2 = savedate2;
    	this.modifydate2 = modifydate2; 
    }
}
