package com.wp.auntweb.DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wp.auntweb.DTO.NoticeDTO;

public class NoticeDAO {
	public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public NoticeDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	  
	  public int insertBoard(NoticeDTO noticedto) throws ClassNotFoundException, SQLException{
		  connectDB();
		  int result = 0;
		  PreparedStatement psm = null;
		  String sql = "insert into notice (num, title, content, savedate, modifydate) values (?,?,?,?,?) "; 
		  psm = conn.prepareStatement(sql); 
		  psm.setInt(1, noticedto.getNum());
		  psm.setString(2, noticedto.getTitle());
		  psm.setString(3, noticedto.getContent());
		  psm.setTimestamp(4, noticedto.getSavedate());
		  psm.setTimestamp(5, noticedto.getModifydate());
		  result = psm.executeUpdate();
		  psm.close();
		  disconnectDB();
		  return result; 
	  }
	  
	  public int updateBoard(NoticeDTO noticedto) throws ClassNotFoundException, SQLException{
		  connectDB();
		  int result = 0;
		  PreparedStatement psm = null;
		  String sql = "update notice set content = ?, modifydate = ? where num = ?"; 
		  psm = conn.prepareStatement(sql);
		  psm.setString(1, noticedto.getContent());
		  psm.setTimestamp(2, noticedto.getModifydate());
		  psm.setInt(3, noticedto.getNum());
		  result = psm.executeUpdate();
		  psm.close();
		  disconnectDB();
		  return result; 
	  }
	  
	  public int deleteBoard(int num) throws ClassNotFoundException, SQLException{
		  connectDB();
		  int result = 0;
		  PreparedStatement psm = null;
		  String sql = "delete from notice where num = ?";
		  psm = conn.prepareStatement(sql);
		  psm.setInt(1, num);
		  result = psm.executeUpdate();
		  psm.close();
		  disconnectDB();
		  return result; 
	  }
	  
	  public int getCountBoardNumber() throws ClassNotFoundException, SQLException {
		  connectDB();
		  int countnum = 0;
		  ResultSet rs = null;
		  Statement sm = null;
		  sm = conn.createStatement();
		  String sql = "select count(num) as countnum from notice";
		  rs = sm.executeQuery(sql);
		  if(rs.next()) {
			  countnum = rs.getInt("countnum"); 
		  }
		  sm.close();
		  rs.close();
		  disconnectDB();
		  return countnum; 
	  }
	  
	  public int getMaxBoardNumber() throws ClassNotFoundException, SQLException{
		  connectDB();
		  int maxnumber = 0;
		  ResultSet rs = null;
		  Statement sm = null;
		  sm = conn.createStatement();
		  String sql = "select max(num) as maxnumber from notice";
		  rs = sm.executeQuery(sql);
		  
		  if(rs.next()) {
			  maxnumber = rs.getInt("maxnumber"); 
		  }
		  
		  //System.out.println("maxnumber: " + maxnumber);
		  sm.close();
		  rs.close();
		  disconnectDB();
		  return maxnumber; 
	  }
	  
	  //For Search By Word 
	  public List<NoticeDTO> SearchBoardList(String word) throws ClassNotFoundException, SQLException
	   {
		   ArrayList<NoticeDTO> boardlist = null;
		   String sql = null;
		   connectDB();
		   ResultSet rs = null;
		   PreparedStatement psm = null;
		   
		   sql = "select * from notice where title like ? order by num";
		   psm = conn.prepareStatement(sql);
		   psm.setString(1, '%' + word + '%');
		   
		   rs = psm.executeQuery();
		   if(rs.isBeforeFirst())
		   {
			   boardlist = new ArrayList<NoticeDTO>();
			   while(rs.next())
			   {
				   NoticeDTO noticedto = new NoticeDTO();
				   noticedto.setNum(rs.getInt("num"));
				   noticedto.setTitle(rs.getString("title"));
				   noticedto.setContent(rs.getString("content"));
				   noticedto.setSavedate(rs.getTimestamp("savedate"));
				   noticedto.setModifydate(rs.getTimestamp("modifydate"));
				   boardlist.add(noticedto);
			   }
		   }
		   psm.close();
		   rs.close();
		   disconnectDB();
		   return boardlist;
	   }
	  
	  
	  public List<NoticeDTO> getBoardList(Boolean desc) throws ClassNotFoundException, SQLException
	   {
		   ArrayList<NoticeDTO> boardlist = null;
		   String sql = null;
		   connectDB();
		   ResultSet rs = null;
		   Statement sm = null;
		   sm = conn.createStatement();
		   if(desc) {
			   sql = "select * from notice order by num desc";
		   }
		   else {
			   sql = "select * from notice order by num";
		   }
		   rs = sm.executeQuery(sql);
		   if(rs.isBeforeFirst())
		   {
			   boardlist = new ArrayList<NoticeDTO>();
			   while(rs.next())
			   {
				   NoticeDTO boarddo = new NoticeDTO();
				   boarddo.setNum(rs.getInt("num"));
				   boarddo.setTitle(rs.getString("title"));
				   boarddo.setContent(rs.getString("content"));
				   boarddo.setSavedate(rs.getTimestamp("savedate"));
				   boarddo.setModifydate(rs.getTimestamp("modifydate"));
				   boardlist.add(boarddo);
			   }
		   }
		   sm.close();
		   rs.close();
		   disconnectDB();
		   return boardlist;
	   }
	 
	  public Map<String, String> getBoardListByNum(int num, boolean br) throws ClassNotFoundException, SQLException{
		  Map<String, String> boardlist = new HashMap<String, String>();
		  String sql = "select * from notice where num = ?";
		  connectDB();
		  PreparedStatement psm = null;
		  ResultSet rs = null;
		  psm = conn.prepareStatement(sql);
		  psm.setInt(1, num);
		  rs = psm.executeQuery();
		  if(rs.next()) {
			  boardlist.put("num", num + "");
			  boardlist.put("title", rs.getString("title"));
			  if(br) { //HTML 
				  boardlist.put("content", rs.getString("content").replace(System.getProperty("line.separator"), "<br>")); 
			  }
			  else {
				  boardlist.put("content", rs.getString("content")); 
			  }
			  boardlist.put("savedate", rs.getString("savedate")); 
			  boardlist.put("modifydate", rs.getString("modifydate")); 
		  }
		  psm.close();
		  rs.close();
		  disconnectDB();
		  return boardlist; 
	  }
}
