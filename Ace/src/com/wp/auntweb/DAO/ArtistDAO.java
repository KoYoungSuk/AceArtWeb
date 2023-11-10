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

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.wp.auntweb.DTO.ArtistDTO;

public class ArtistDAO {
	   public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public ArtistDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	   
	   public int insertArtist(ArtistDTO artistdto) throws ClassNotFoundException, SQLException {
		   connectDB();
		   int result = 0;
		   String sql = "insert into artist values (?,?,?,?,?,?,?)";
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, artistdto.getNum());
		   psm.setString(2, artistdto.getName());
		   psm.setString(3, artistdto.getCareer());
		   psm.setString(4, artistdto.getWork1());
		   psm.setString(5, artistdto.getWork2());
		   psm.setTimestamp(6, artistdto.getSavedate());
		   psm.setTimestamp(7, artistdto.getModifydate());
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	  
	   public int updateArtist(ArtistDTO artistdto) throws ClassNotFoundException, SQLException{
		   connectDB();
		   int result = 0;
		   String sql = null;
		   int compare_num = 0;
		   /*
		    * compare_num = 0:  모두 다 NULL일때
		    * compare_num = 1:  Work1만 NULL일때
		    * compare_num = 2:  Work2만 NULL일때
		    * compare_num = 3:  모두 다 NULL이 아닐때 
		    * 
		    */
		   if(artistdto.getWork1() == null && artistdto.getWork2() == null) { //모두 다 NULL일때 
			   sql = "update artist set name=?, career=?, modifydate=? where num=?"; 
			   compare_num = 0; 
		   }
		   else {
			   if(artistdto.getWork1() == null) {
				   sql = "update artist set name=?, career=?, work2=?, modifydate=? where num=?"; 
				   compare_num = 1; 
			   }
			   else if(artistdto.getWork2() == null) {
				   sql = "update artist set name=?, career=?, work1=?, modifydate=? where num=?"; 
				   compare_num = 2; 
			   }
			   else if(artistdto.getWork1() != null && artistdto.getWork2() != null) {
				   sql = "update artist set name=?, career=?, work1=?, work2=?, modifydate=? where num=?";
				   compare_num = 3; 
			   }
		   }
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setString(1, artistdto.getName());
		   psm.setString(2, artistdto.getCareer());
		   if(compare_num == 0) {
			   psm.setTimestamp(3, artistdto.getModifydate());
			   psm.setInt(4, artistdto.getNum());
		   }
		   else if(compare_num == 1) {
			   psm.setString(3, artistdto.getWork2());
			   psm.setTimestamp(4, artistdto.getModifydate());
			   psm.setInt(5, artistdto.getNum());
		   }
		   else if(compare_num == 2) {
			   psm.setString(3, artistdto.getWork1());
			   psm.setTimestamp(4, artistdto.getModifydate());
			   psm.setInt(5, artistdto.getNum());
		   }
		   else if(compare_num == 3) {
			   psm.setString(3, artistdto.getWork1());
			   psm.setString(4, artistdto.getWork2());
			   psm.setTimestamp(5, artistdto.getModifydate());
			   psm.setInt(6, artistdto.getNum());
		   }
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int deleteArtist(int num) throws ClassNotFoundException, SQLException{
		   connectDB();
		   int result = 0;
		   String sql = "delete from artist where num=?";
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	  public List<ArtistDTO> getTotalartistList(Boolean br) throws ClassNotFoundException, SQLException{
		  List<ArtistDTO> totalartistlist = new ArrayList<ArtistDTO>(); 
		  connectDB();
		  String sql = "select * from artist order by num";
		  Statement sm = null;
		  ResultSet rs = null;
		  sm = conn.createStatement();
		  rs = sm.executeQuery(sql);
		  
		  if(rs.isBeforeFirst()) {
			  while(rs.next()) {
				  ArtistDTO artistdto = new ArtistDTO();
				  artistdto.setNum(rs.getInt("num"));
				  artistdto.setName(rs.getString("name"));
				  if(br) {
					  artistdto.setCareer(rs.getString("career").replace(System.getProperty("line.separator"), XssPreventer.unescape("&lt;br&gt;")));
				  }
				  else {
					  artistdto.setCareer(rs.getString("career")); 
				  }
				  artistdto.setWork1(rs.getString("work1"));
				  artistdto.setWork2(rs.getString("work2"));
				  artistdto.setSavedate(rs.getTimestamp("savedate")); 
				  artistdto.setModifydate(rs.getTimestamp("modifydate"));
				  totalartistlist.add(artistdto); 
			  }
		  }
		  rs.close();
		  sm.close();
		  disconnectDB();
		  return totalartistlist;  
	  }
	  
	  public Map<String, String> getArtistlistByNum(int num, Boolean br) throws ClassNotFoundException, SQLException{
		  connectDB();
		  Map<String, String> artistlist = new HashMap<String, String>();
		  String sql = "select * from artist where num = ?";
		  PreparedStatement psm = null;
		  psm = conn.prepareStatement(sql);
		  psm.setInt(1, num);
		  ResultSet rs = psm.executeQuery();
		  if(rs.next()) {
			  artistlist.put("num",  rs.getInt("num") + "");
			  artistlist.put("name", rs.getString("name"));
			  if(br) {
				  artistlist.put("career", rs.getString("career").replace(System.getProperty("line.separator"), "<br>"));
			  }
			  else {
				  artistlist.put("career", rs.getString("career"));
			  }
			  artistlist.put("work1", rs.getString("work1"));
			  artistlist.put("work2", rs.getString("work2"));
			  artistlist.put("savedate", rs.getString("savedate"));
			  artistlist.put("modifydate", rs.getString("modifydate")); 
		  }
		  rs.close();
		  psm.close();
		  disconnectDB();
		  return artistlist; 
	  }
	  
	  public int getCountnum() throws ClassNotFoundException, SQLException{
		  connectDB();
		  int countnum = 0;
		  String sql = "select count(num) as countnum from artist";
		  Statement sm = null;
		  ResultSet rs = null;
		  sm = conn.createStatement();
		  rs = sm.executeQuery(sql);
		  if(rs.next()) {
			  countnum = rs.getInt("countnum"); 
		  }
		  rs.close();
		  sm.close();
		  disconnectDB();
		  return countnum; 
	  }
	  public int getMaxnum() throws ClassNotFoundException, SQLException{
		  connectDB();
		  int maxnum = 0;
		  String sql = "select max(num) as maxnum from artist";
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
}
