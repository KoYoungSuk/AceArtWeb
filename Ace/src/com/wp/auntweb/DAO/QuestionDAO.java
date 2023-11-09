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

import com.wp.auntweb.DTO.QuestionDTO;

public class QuestionDAO {
	   public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public QuestionDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	   
	   public int insertQuestion(QuestionDTO questiondto) throws ClassNotFoundException, SQLException {
		   connectDB();
		   int result = 0;
		   String sql = "insert into question values (?,?,?,?,?,?,?)"; 
		   PreparedStatement psm = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, questiondto.getNum());
		   psm.setString(2, questiondto.getTitle());
		   psm.setString(3, questiondto.getContent());
		   psm.setString(4, questiondto.getUser());
		   psm.setString(5, questiondto.getAccess());
		   psm.setTimestamp(6, questiondto.getSavedate());
		   psm.setTimestamp(7, questiondto.getModifydate()); 
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB(); 
		   return result; 
	   }
	   
	   public int updateQuestion(QuestionDTO questiondto) throws ClassNotFoundException, SQLException{
		   connectDB();
		   int result = 0;
		   String sql = "update question set title=?, content=?, \"ACCESS\"=?, modifydate=? where num=? and \"USER\" = ?";
		   PreparedStatement psm = conn.prepareStatement(sql); 
		   psm.setString(1, questiondto.getTitle());
		   psm.setString(2, questiondto.getContent());
		   psm.setString(3, questiondto.getAccess());
		   psm.setTimestamp(4, questiondto.getModifydate());
		   psm.setInt(5, questiondto.getNum());
		   psm.setString(6, questiondto.getUser());
		   result = psm.executeUpdate();
		   psm.close(); 
		   disconnectDB();
		   return result; 
	   }

	   public int deleteQuestion(int num) throws ClassNotFoundException, SQLException{
		   connectDB();
		   int result = 0;
		   String sql = "delete from question where num = ?";
		   String sql2 = "delete from answer where q_num = ?"; //질문을 삭제하면 답변까지 다 삭제한다.
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   result = psm.executeUpdate();
		   if(result != 0) {
			   if(getCountNumAnswer2(num) > 0) { //답변 게시판에 글이 있으면 
				   psm.close(); 
				   psm = conn.prepareStatement(sql2);
				   psm.setInt(1, num);
				   result = psm.executeUpdate(); 
			   }
		   }
		   psm.close();
		   disconnectDB(); 
		   return result; 
	   }
	   
	   public List<QuestionDTO> getTotalquestionList() throws ClassNotFoundException, SQLException {
		   connectDB();
		   List<QuestionDTO> questionlist = new ArrayList<QuestionDTO>();
		   String sql = new String("select q.*, a.num AS num2, a.q_num AS QNUM, a.title as title2, a.content AS content2, a.\"ACCESS\" AS access2, a.\"USER\" AS user2, a.savedate AS savedate2, a.modifydate AS modifydate2 from question q left join answer a on q.num = a.q_num order by q.num, a.num"); 
		   Statement sm = null;
		   ResultSet rs = null;
		   sm = conn.createStatement();
		   rs = sm.executeQuery(sql);
		   
		   if(rs.isBeforeFirst()) {
			   while(rs.next()) {
				   QuestionDTO questiondto = new QuestionDTO();
				   questiondto.setNum(rs.getInt("num"));
				   questiondto.setTitle(rs.getString("title"));
				   questiondto.setContent(rs.getString("content"));
				   questiondto.setUser(rs.getString("user"));
				   questiondto.setAccess(rs.getString("access"));
				   questiondto.setSavedate(rs.getTimestamp("savedate"));
				   questiondto.setModifydate(rs.getTimestamp("modifydate"));
				   questiondto.setNum2(rs.getInt("num2")); 
				   questiondto.setQ_num(rs.getInt("QNUM")); 
				   questiondto.setTitle2(rs.getString("title2"));
				   questiondto.setContent2(rs.getString("content2"));
				   questiondto.setAccess2(rs.getString("access2"));
				   questiondto.setSavedate2(rs.getTimestamp("savedate2"));
				   questiondto.setModifydate2(rs.getTimestamp("modifydate2")); 
				   questionlist.add(questiondto); 
			   }
		   }
		   rs.close();
		   sm.close();
		   disconnectDB();
		   return questionlist; 
	   }
  
	   public Map<String, String> getDetailQuestion(int num, Boolean br) throws ClassNotFoundException, SQLException {
		   connectDB();
		   Map<String, String> detailquestionlist = new HashMap<String, String>(); 
		   String sql = "select * from question where num = ?"; 
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setInt(1, num);
		   ResultSet rs = psm.executeQuery();
		   if(rs.next()) {
			   detailquestionlist.put("num", rs.getInt("num") + "");
			   detailquestionlist.put("title", rs.getString("title"));
			   if(br) {
				   detailquestionlist.put("content", rs.getString("content").replace(System.getProperty("line.separator"), "<br>")); 
			   }
			   else {
				   detailquestionlist.put("content", rs.getString("content")); 
			   }
			   detailquestionlist.put("user", rs.getString("user"));
			   detailquestionlist.put("access", rs.getString("access"));
			   detailquestionlist.put("savedate", rs.getString("savedate"));
			   detailquestionlist.put("modifydate", rs.getString("modifydate")); 
		   }
		   rs.close();
		   psm.close();
		   disconnectDB();
		   return detailquestionlist; 
	   }
	   public int getMaxNum() throws ClassNotFoundException, SQLException {
		   connectDB();
		   int result = 0;
		   String sql = "select max(num) as maxnum from question";
		   Statement sm = conn.createStatement();
		   ResultSet rs = sm.executeQuery(sql);
		   if(rs.next()) {
			   result = rs.getInt("maxnum"); 
		   }
		   rs.close();
		   sm.close();
		   disconnectDB(); 
		   return result; 
	   }
	   
	   public int getCountNum() throws ClassNotFoundException, SQLException{
		   connectDB();
		   int result = 0;
		   String sql = "select count(num) as countnum from question";
		   Statement sm = conn.createStatement();
		   ResultSet rs = sm.executeQuery(sql);
		   if(rs.next()) {
			   result = rs.getInt("countnum");
		   }
		   rs.close();
		   sm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   public int getCountNumAnswer2(int q_num) throws ClassNotFoundException, SQLException {
		   connectDB();
		   int result = 0;
		   String sql = "select count(*) as countnum from answer where q_num = ?";
		   PreparedStatement psm = null;
		   ResultSet rs = null;
		   psm = conn.prepareStatement(sql);
		   psm.setInt(1, q_num);
		   rs = psm.executeQuery();
		   if(rs.next()) {
			   result = rs.getInt("countnum"); 
		   }
		   rs.close();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
}
