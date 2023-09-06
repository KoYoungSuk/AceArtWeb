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

import com.wp.auntweb.DTO.MemberDTO;

public class MemberDAO {
	 public Connection conn = null;
	   private String jdbc_driver;
	   private String db_url;
	   private String db_id;
	   private String db_pw;
	   
	   public MemberDAO(String jdbc_driver, String db_url, String db_id, String db_pw)
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
	   
	   //insert Member Information
	   public int insertMember(MemberDTO memberdto) throws ClassNotFoundException, SQLException
	   {
		   int result = 0;
		   connectDB();
		   String sql = "insert into member (id, password, name, birthday, joindate) values (?,?,?,?,?)"; 
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, memberdto.getId());
		   psm.setString(2, memberdto.getPassword());
		   psm.setString(3, memberdto.getName());
		   psm.setString(4, memberdto.getBirthday());
		   psm.setTimestamp(5, memberdto.getJoindate()); 
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   //Update Member Information
	   public int updateMember(MemberDTO memberdto) throws ClassNotFoundException, SQLException
	   {
		   int result = 0;
		   connectDB();
		   String sql = "update member set password=?, name=?, birthday=? where id=?";
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, memberdto.getPassword());
		   psm.setString(2, memberdto.getName());
		   psm.setString(3, memberdto.getBirthday());
		   psm.setString(4, memberdto.getId());
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   //Delete Member Information
	   public int deleteMember(String id) throws ClassNotFoundException, SQLException
	   {
		   int result = 0;
		   connectDB();
		   String sql = "delete from member where id = ?";
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, id);
		   result = psm.executeUpdate();
		   psm.close();
		   disconnectDB();
		   return result; 
	   }
	   
	   //Get Total Member Information
	   public List<MemberDTO> GetTotalMemberList() throws ClassNotFoundException, SQLException
	   {
		   List<MemberDTO> memberlist = new ArrayList<MemberDTO>(); 
		   connectDB();
		   String sql = "select * from member order by id";
		   Statement sm = null;
		   ResultSet rs = null;
		   sm = conn.createStatement();
		   rs = sm.executeQuery(sql);
		   if(rs.isBeforeFirst())
		   {
			   memberlist = new ArrayList<MemberDTO>();
			   while(rs.next())
			   {
				   MemberDTO memberdo = new MemberDTO();
				   memberdo.setId(rs.getString("id"));
				   memberdo.setPassword(rs.getString("password"));
				   memberdo.setName(rs.getString("name"));
				   memberdo.setBirthday(rs.getString("birthday"));
				   memberdo.setJoindate(rs.getTimestamp("joindate"));
				   memberlist.add(memberdo);
			   }
		   }
		   rs.close();
		   sm.close();
		   disconnectDB();
		   return memberlist; 
	   }
	   
	   //Get Member Information By Id. (Use for Login)
	   public Map<String, String> GetMemberListById(String id) throws ClassNotFoundException, SQLException
	   {
		   Map<String, String> memberlist = new HashMap<String, String>();
		   connectDB();
		   String sql = "select * from member where id = ?"; 
		   PreparedStatement psm = conn.prepareStatement(sql);
		   psm.setString(1, id);
		   ResultSet rs = psm.executeQuery();
		   if(rs.next())
		   {
			   memberlist.put("id", rs.getString("id"));
			   memberlist.put("password", rs.getString("password"));
			   memberlist.put("name", rs.getString("name"));
			   memberlist.put("birthday", rs.getString("birthday"));
			   memberlist.put("joindate", rs.getTimestamp("joindate").toString());
		   }
		   rs.close();
		   psm.close();
		   disconnectDB();
		   return memberlist; 
	   }
}
