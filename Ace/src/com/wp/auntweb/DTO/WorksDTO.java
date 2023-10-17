package com.wp.auntweb.DTO;

import java.sql.Timestamp;

public class WorksDTO {
    public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getInstalldate() {
		return installdate;
	}
	public void setInstalldate(String installdate) {
		this.installdate = installdate;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	int num;
    String name;
    String picture;
    String installdate;
    Timestamp savedate;
    Timestamp modifydate;
    String description;
    public WorksDTO() {
    	
    }
    public WorksDTO(int num, String name, String picture, String installdate, Timestamp savedate, Timestamp modifydate, String description) {
    	this.num = num;
    	this.name = name;
    	this.picture = picture;
    	this.installdate = installdate;
    	this.savedate = savedate;
    	this.modifydate = modifydate; 
    	this.description = description; 
    }
}
