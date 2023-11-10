package com.wp.auntweb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.wp.auntweb.DTO.QuestionDTO;

public class AnswerDAO {
	   public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public AnswerDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	   
	   public int insertAnswer(QuestionDTO questiondto) throws ClassNotFoundException, SQLException {
		   connectDB();
		   String sql = "insert into answer values (?,?,?,?,?,?,?,?)";
		   int result = 0;
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, questiondto.getNum2());
		   psm.setInt(2, questiondto.getQ_num());
		   psm.setString(3, questiondto.getTitle2());
		   psm.setString(4, questiondto.getContent2());
		   psm.setString(5, questiondto.getAccess2());
		   psm.setString(6, questiondto.getUser2());
		   psm.setTimestamp(7, questiondto.getSavedate2());
		   psm.setTimestamp(8, questiondto.getModifydate2()); 
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int updateAnswer(QuestionDTO questiondto) throws ClassNotFoundException, SQLException{
		   connectDB();
		   String sql = "update answer set content=?, modifydate=? where num=?";
		   int result = 0;
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, questiondto.getContent2());
		   psm.setTimestamp(2, questiondto.getModifydate2());
		   psm.setInt(3, questiondto.getNum2());
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   //Method OverLoading(메소드 오버로딩): 같은 메소드, 다른 매개변수 개수(이미 DTO 쪽에 많이썼음....) 
	   //질문 게시물을 수정하였을때 질문 게시물의 제목과 접근모드를 변경했을 경우 그 질문게시판에 답변한 답변 게시물의 제목과 접근모드도 바꿔주어야 한다. 
	   public int updateAnswer(String title, String access, int q_num) throws ClassNotFoundException, SQLException{
		   connectDB();
		   String sql = "update answer set title=?, \"ACCESS\" = ? where q_num=?";
		   int result = 0;
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, title);
		   psm.setString(2, access);
		   psm.setInt(3, q_num);
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int deleteAnswer(int num) throws ClassNotFoundException, SQLException {
		   connectDB();
		   String sql = "delete from answer where num = ?";
		   int result = 0;
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public Map<String, String> getAnswerList(int num, Boolean br) throws ClassNotFoundException, SQLException {
		   connectDB();
		   Map<String, String> answerlist = new HashMap<String, String>();
		   String sql = "select * from answer where num = ?";
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   ResultSet rs = psm.executeQuery();
		   if(rs.next()) {
			   answerlist.put("num", rs.getInt("num") + "");
			   answerlist.put("q_num", rs.getInt("q_num") + "");
			   answerlist.put("title", rs.getString("title"));
			   if(br) {
				   answerlist.put("content", rs.getString("content").replace(System.getProperty("line.separator"), "<br>")); 
			   }
			   else {
				   answerlist.put("content", rs.getString("content"));
			   }
			   answerlist.put("access", rs.getString("access"));
			   answerlist.put("user", rs.getString("user"));
			   answerlist.put("savedate", rs.getString("savedate"));
			   answerlist.put("modifydate", rs.getString("modifydate")); 
		   }
		   rs.close();
		   psm.close();
		   disconnectDB();
		   return answerlist; 
	   }
	   
	   public int getMaxnum() throws ClassNotFoundException, SQLException {
		   connectDB();
		   String sql = "select max(num) as maxnum from answer";
		   int result = 0;
		   Statement sm = null;
		   ResultSet rs = null;
		   sm = conn.createStatement();
		   rs = sm.executeQuery(sql);
		   
		   if(rs.next()) {
			   result = rs.getInt("maxnum"); 
		   }
		   rs.close();
		   sm.close();
		   disconnectDB();
		   return result; 
	   }
}
