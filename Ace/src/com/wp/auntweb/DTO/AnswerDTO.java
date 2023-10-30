package com.wp.auntweb.DTO;

import java.sql.Timestamp;

public class AnswerDTO {
    public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void getQNum(int q_num) {
		this.q_num = q_num; 
	}
	public int setQNum() {
		return q_num; 
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
	int q_num; 
    String title;
    String content;
    String user;
    String access;
    Timestamp savedate;
    Timestamp modifydate;
    public AnswerDTO() {
    	
    }
    
    public AnswerDTO(int num, int q_num, String title, String content, String user, String access, Timestamp savedate, Timestamp modifydate) {
    	this.num = num;
    	this.q_num = q_num; 
    	this.title = title;
    	this.content = content;
    	this.user = user;
    	this.access = access;
    	this.savedate = savedate;
    	this.modifydate = modifydate; 
    }
    
}
