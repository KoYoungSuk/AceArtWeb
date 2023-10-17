package com.wp.auntweb.DTO;
import java.sql.Timestamp;

public class ArtistDTO {
     int num;
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
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getWork1() {
		return work1;
	}
	public void setWork1(String work1) {
		this.work1 = work1;
	}
	public String getWork2() {
		return work2;
	}
	public void setWork2(String work2) {
		this.work2 = work2;
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
	String name;
    String career;
    String work1;
    String work2;
    Timestamp savedate; 
    Timestamp modifydate; 
    
   public ArtistDTO(){
	   
   }
   
   public ArtistDTO(int num, String name, String career, String work1, String work2, Timestamp savedate, Timestamp modifydate) {
	   this.num = num;
	   this.name = name;
	   this.career = career;
	   this.work1 = work1;
	   this.work2 = work2;
	   this.savedate = savedate; 
	   this.modifydate = modifydate; 
   }
} 
