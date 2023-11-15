package com.wp.auntweb;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.wp.auntweb.DAO.MemberDAO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Global g = new Global(response);
		g.errorcode(403); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String viewName = null; 
		Global g = new Global(response);
		
		//Parameters from HTML
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try
  	    {
  	    	if(id != null)
  	    	{
  	    		MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
  	  	    	Map<String, String> memberlist = memberdao.GetMemberListById(id); 
  	  	    	
  	  	    	if(memberlist != null)
  	  	    	{
  	  	    		String password_db = memberlist.get("password"); 
  	  	    		if(BCrypt.checkpw(password, password_db)) //BCrypt 비밀번호 체크 
  	  	    		{
  	  	    			viewName = "index.jsp?page=1";
  	  	    			session.setAttribute("id", id);
  	  	    			session.setAttribute("name", memberlist.get("name"));
  	  	    		}
  	  	    	}
  	    	}
  	    	else
  	    	{
  	    		g.jsmessage("Null Error Message."); 
  	    	}
  	    }
  	    catch(Exception e)
  	    { 
  	    	g.jsmessage(e.getMessage());
  	    }
  	    
  	    if(viewName != null)
  	    {
  	    	response.sendRedirect(viewName);
  	    }
  	    else
  	    {
  	    	g.jsmessage("로그인 오류: 아이디와 비밀번호가 올바르지 않음. (Incorrected ID or Password.)"); 
  	    }
	}

}
