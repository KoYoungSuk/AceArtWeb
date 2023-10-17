package com.wp.auntweb.DTO;

import java.sql.Timestamp;

public class MemberDTO {
    String id;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Timestamp getJoindate() {
		return joindate;
	}
	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}
	
	public String getEmail() {
		return email; 
	}
	public void setEmail(String email) {
		this.email = email; 
	}
	public MemberDTO(String id, String password, String name, String birthday, Timestamp joindate, String email)
	{
		this.id = id;
		this.password = password;
		this.name = name; 
		this.birthday = birthday;
		this.joindate = joindate; 
		this.email = email; 
	}
	
	public MemberDTO()
	{
		
	}
	String password;
    String name;
    String birthday;
    Timestamp joindate;
    String email; 
}
