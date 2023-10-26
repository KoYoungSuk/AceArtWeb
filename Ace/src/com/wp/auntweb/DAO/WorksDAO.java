package com.wp.auntweb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wp.auntweb.DTO.WorksDTO;

public class WorksDAO {
	public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public WorksDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
	   {
		   this.jdbc_driver = jdbc_driver;
		   this.db_url = db_url;
		   this.db_id = db_id;
		   this.db_pw = db_pw;
	   }
	   
	   //DB Connection 
	   public void connectDB() throws SQLException, ClassNotFoundException
	   {
		   Class.forName(jdbc_driver);
		   conn = DriverManager.getConnection(db_url, db_id, db_pw);
	   }
	   
	  //DB Disconnection 
	   public void disconnectDB() throws SQLException
	   {
		   if(conn != null)
		   {
			   conn.close();
			   conn = null;
		   }
	   }
	   
	   public int insertWorks(WorksDTO worksdto) throws SQLException, ClassNotFoundException {
		   connectDB();
		   String sql = "insert into works (num, name, picture, installdate, savedate, modifydate, description) values (?,?,?,?,?,?,?)";
		   int result = 0;
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, worksdto.getNum());
		   psm.setString(2, worksdto.getName());
		   psm.setString(3, worksdto.getPicture());
		   psm.setString(4, worksdto.getInstalldate());
		   psm.setTimestamp(5, worksdto.getSavedate());
		   psm.setTimestamp(6, worksdto.getModifydate());
		   psm.setString(7, worksdto.getDescription());
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int updateWorks(WorksDTO worksdto) throws SQLException, ClassNotFoundException{
		   connectDB();
		   String sql = null;
		   if(worksdto.getPicture() == null) {
			   sql = "update works set name=?, installdate=?, modifydate=?, description=? where num=?"; 
		   }
		   else {
			   sql = "update works set name=?, picture=?, installdate=?, modifydate=?, description=? where num=?"; 
		   }
		   int result = 0;
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setString(1, worksdto.getName());
		   if(worksdto.getPicture() == null) {
			   psm.setString(2, worksdto.getInstalldate());
			   psm.setTimestamp(3, worksdto.getModifydate());
			   psm.setString(4, worksdto.getDescription());
			   psm.setInt(5, worksdto.getNum());
		   }
		   else {
			   psm.setString(2, worksdto.getPicture());
			   psm.setString(3, worksdto.getInstalldate());
			   psm.setTimestamp(4, worksdto.getModifydate());
			   psm.setString(5, worksdto.getDescription());
			   psm.setInt(6, worksdto.getNum());
		   }
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int deleteWorks(int num) throws SQLException, ClassNotFoundException{
		   connectDB();
		   int result = 0;
		   String sql = "delete from works where num=?";
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int getMaxNum() throws SQLException, ClassNotFoundException{
		   connectDB();
		   int maxnum = 0;
		   String sql = "select max(num) as maxnum from works";
		   Statement sm = null;
		   ResultSet rs = null;
		   sm = conn.createStatement();
		   rs = sm.executeQuery(sql);
		   if(rs.next()) {
			   maxnum = rs.getInt("maxnum"); 
		   }
		   rs.close();
		   sm.close();
		   disconnectDB();
		   return maxnum; 
	   }
	   
	   public List<WorksDTO> getTotalWorksList(Boolean desc) throws ClassNotFoundException, SQLException{
			  List<WorksDTO> totalworkslist = null;
			  connectDB();
			  String sql = null;
			  if(desc) {
				  sql = "select * from works order by num desc";
			  }
			  else {
				  sql = "select * from works order by num"; 
			  }
			  Statement sm = null;
			  ResultSet rs = null;
			  sm = conn.createStatement();
			  rs = sm.executeQuery(sql);
		      
			  if(rs.isBeforeFirst()) {
				  totalworkslist = new ArrayList<WorksDTO>();
				  while(rs.next()) {
					  WorksDTO worksdto = new WorksDTO();
					  worksdto.setNum(rs.getInt("num"));
					  worksdto.setName(rs.getString("name"));
					  worksdto.setPicture(rs.getString("picture"));
					  worksdto.setInstalldate(rs.getString("installdate")); 
					  worksdto.setSavedate(rs.getTimestamp("savedate"));
					  worksdto.setModifydate(rs.getTimestamp("modifydate"));
					  worksdto.setDescription(rs.getString("description")); 
					  totalworkslist.add(worksdto); 
				  }
			  }
			  
			  sm.close();
			  rs.close(); 
			  disconnectDB();
			  return totalworkslist; 
	   }
	
	   
	   public Map<String, String> getWorksListByNum(int num, Boolean br) throws ClassNotFoundException, SQLException{
		   Map<String, String> workslist = new HashMap<String, String>(); 
		   connectDB();
		   String sql = "select * from works where num = ?";
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   ResultSet rs = null;
		   rs = psm.executeQuery();
		   if(rs.next()) {
			   workslist.put("num", num + "");
			   workslist.put("name", rs.getString("name"));
			   if(rs.getString("description") != null) {
				   if(br) {
					   workslist.put("description", rs.getString("description").replace(System.getProperty("line.separator"), "<br>")); 
				   }
				   else {
					   workslist.put("description", rs.getString("description")); 
				   }
			   }
			   else {
				   workslist.put("description", null); 
			   }
			   workslist.put("picture", rs.getString("picture")); 
			   workslist.put("installdate", rs.getString("installdate"));
			   workslist.put("savedate", rs.getString("savedate"));
			   workslist.put("modifydate", rs.getString("modifydate")); 
		   }
		   psm.close();
		   rs.close(); 
		   disconnectDB();
		   return workslist; 
	  }
	   
}
