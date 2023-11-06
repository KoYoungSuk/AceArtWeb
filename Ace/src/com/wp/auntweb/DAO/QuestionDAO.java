package com.wp.auntweb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	   
}
