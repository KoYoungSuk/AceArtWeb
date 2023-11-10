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

import com.wp.auntweb.DTO.BoardDTO;

public class BoardDAO {
	public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public BoardDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	   
	   public int insertBoard(BoardDTO boarddto) throws ClassNotFoundException, SQLException {
		   connectDB();
		   String sql = "insert into board values (?,?,?,?,?,?,?) ";
		   int result = 0;
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql); 
		   psm.setInt(1, boarddto.getNum());
		   psm.setString(2, boarddto.getTitle());
		   psm.setString(3, boarddto.getContent());
		   psm.setString(4, boarddto.getAccess());
		   psm.setString(5, boarddto.getFiles()); 
		   psm.setTimestamp(6, boarddto.getSavedate());
		   psm.setTimestamp(7, boarddto.getModifydate());
		   result = psm.executeUpdate();
		   psm.close(); 
		   disconnectDB(); 
		   return result; 
	   }
	   
	   public int updateBoard(BoardDTO boarddto) throws ClassNotFoundException, SQLException {
		   connectDB();
		   String sql = null;
		   if(boarddto.getFiles() == null) { //파일을 바꾸지 않는다. 
			   sql = "update board set title=?, content=?, modifydate=? where num=?"; 
		   }
		   else { //파일을 바꾼다. 
			   sql = "update board set title=?, content=?, files=?, modifydate=? where num=?"; 
		   }
		   int result = 0;
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql); 
		   psm.setString(1, boarddto.getTitle());
		   psm.setString(2, boarddto.getContent());
		   if(boarddto.getFiles() == null) {
			   psm.setTimestamp(3, boarddto.getModifydate());
			   psm.setInt(4, boarddto.getNum());
		   }
		   else {
			   psm.setString(3, boarddto.getFiles()); 
			   psm.setTimestamp(4, boarddto.getModifydate());
			   psm.setInt(5, boarddto.getNum());
		   }
		   result = psm.executeUpdate();
		   psm.close(); 
		   disconnectDB(); 
		   return result; 
	   }
	   
	   
	  public int deleteBoard(int num) throws ClassNotFoundException, SQLException{
		  connectDB();
		  String sql = "delete from board where num = ?";
		  int result = 0;
		  PreparedStatement psm = null;
		  psm = conn.prepareStatement(sql);
		  psm.setInt(1, num);
		  result = psm.executeUpdate();
		  psm.close();
		  disconnectDB();
		  return result; 
	  }
	  
	  public int getMaxNumBoard() throws ClassNotFoundException, SQLException{
		  int maxnum = 0;
		  connectDB();
		  String sql = "select max(num) as maxnumber from board";
		  Statement sm = null;
		  ResultSet rs = null;
		  sm = conn.createStatement();
		  rs = sm.executeQuery(sql);
		  
		  if(rs.next()) {
			  maxnum = rs.getInt("maxnumber"); 
		  }
		  
		  sm.close();
		  rs.close();
		  disconnectDB();
		  return maxnum; 
	  }

	  public int getCountBoard() throws ClassNotFoundException, SQLException {
		  int count = 0;
		  connectDB();
		  String sql = "select count(*) as countnum from board";
		  Statement sm = null;
		  ResultSet rs = null;
		  sm = conn.createStatement();
		  rs = sm.executeQuery(sql);
		  
		  if(rs.next()) {
			  count = rs.getInt("countnum"); 
		  }
		  
		  sm.close();
		  rs.close();
		  disconnectDB();
		  return count; 
	  }
	  public List<BoardDTO> getTotalBoardList(Boolean desc) throws ClassNotFoundException, SQLException{
		  List<BoardDTO> totalboardlist = null;
		  connectDB();
		  String sql = null;
		  if(desc) {
			  sql = "select * from board order by num desc";
		  }
		  else {
			  sql = "select * from board order by num"; 
		  }
		  Statement sm = null;
		  ResultSet rs = null;
		  sm = conn.createStatement();
		  rs = sm.executeQuery(sql);
	      
		  if(rs.isBeforeFirst()) {
			  totalboardlist = new ArrayList<BoardDTO>();
			  while(rs.next()) {
				  BoardDTO boarddto = new BoardDTO();
				  boarddto.setNum(rs.getInt("num"));
				  boarddto.setTitle(rs.getString("title"));
				  boarddto.setContent(rs.getString("content"));
				  boarddto.setAccess(rs.getString("access"));
				  boarddto.setFiles(rs.getString("files"));
				  boarddto.setSavedate(rs.getTimestamp("savedate"));
				  boarddto.setModifydate(rs.getTimestamp("modifydate"));
				  totalboardlist.add(boarddto); 
			  }
		  }
		  
		  sm.close();
		  rs.close(); 
		  disconnectDB();
		  return totalboardlist; 
	  }
	   
	  public Map<String, String> getBoardListByNum(int num, Boolean br) throws ClassNotFoundException, SQLException{
		   Map<String, String> boardlist = new HashMap<String, String>(); 
		   connectDB();
		   String sql = "select * from board where num = ?";
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   ResultSet rs = null;
		   rs = psm.executeQuery();
		   if(rs.next()) {
			   boardlist.put("num", num + "");
			   boardlist.put("title", rs.getString("title"));
			   if(br) {
				   boardlist.put("content", rs.getString("content").replace(System.getProperty("line.separator"), "<br>")); 
			   }
			   else {
				   boardlist.put("content", rs.getString("content")); 
			   }
			   boardlist.put("access", rs.getString("access")); 
			   boardlist.put("files", rs.getString("files"));
			   boardlist.put("savedate", rs.getString("savedate"));
			   boardlist.put("modifydate", rs.getString("modifydate")); 
		   }
		   psm.close();
		   rs.close(); 
		   disconnectDB();
		   return boardlist; 
	  }
}
