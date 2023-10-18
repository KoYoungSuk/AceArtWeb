package com.wp.auntweb;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.auntweb.DAO.MemberDAO;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/deletemember.do")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String viewName = null;
		Global g = new Global(response);
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try
  	    {
  	    	MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    	
  	    	Map<String, String> memberlist = memberdao.GetMemberListById(id);
  	    	
  	    	if(memberlist != null)
  	    	{
  	    		viewName = "index.jsp?page=16";  
  	    		session.setAttribute("birthday", memberlist.get("birthday"));
  	    		session.setAttribute("joindate", memberlist.get("joindate"));
  	    		session.setAttribute("email", memberlist.get("email")); 
  	    	}
  	    	else
  	    	{
  	    		g.jsmessage("Null Error Message.");
  	    	}
  	    }
  	    catch(Exception ex)
  	    {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    if(viewName != null)
  	    {
  	    	RequestDispatcher view = request.getRequestDispatcher(viewName);
  		    view.forward(request, response);
  	    }
  	    else
  	    {
  	    	g.jsmessage("알수없는 오류입니다."); 
  	    }
  	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Global g = new Global(response); 
		String viewName = null;
	    HttpSession session = request.getSession();
	    
	    String id = (String)session.getAttribute("id"); 
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try {
  	    	MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw); 
  	    	int result = memberdao.deleteMember(id);
  	    	
  	    	if(result != 0) {
  	    		session.invalidate();
  	    		viewName = "index.jsp?page=1"; 
  	    	}
  	    	else {
  	    		g.jsmessage("Unknown Error Message");
  	    	}
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    if(viewName != null) {
  	    	response.sendRedirect(viewName); 
  	    }
  	    else {
  	    	g.jsmessage("삭제에 실패하였습니다.");
  	    }
	}

}
