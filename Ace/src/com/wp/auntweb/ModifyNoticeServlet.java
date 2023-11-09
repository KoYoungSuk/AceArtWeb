package com.wp.auntweb;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.wp.auntweb.DAO.NoticeDAO;
import com.wp.auntweb.DTO.NoticeDTO;

/**
 * Servlet implementation class ModifyNoticeServlet
 */
@WebServlet("/modifynoticeboard.do")
public class ModifyNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyNoticeServlet() {
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
	    Global g = new Global(response);
	    String viewName = null;
	    HttpSession session = request.getSession();
	    
	    int num = Integer.parseInt(request.getParameter("num"));
	    String id = (String)session.getAttribute("id");
	    

		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try {
  	    	if(id.equals("admin")) {
  	    		NoticeDAO noticedao = new NoticeDAO(JDBC_Driver, db_url, db_id, db_pw);
  	  	    	
  	  	    	Map<String, String> detailnoticelist = noticedao.getBoardListByNum(num, false);
  	  	    	
  	  	    	if(detailnoticelist != null) {
  	  	    		session.setAttribute("detailnoticelist", detailnoticelist);
  	  	    		viewName = "index.jsp?page=20"; 
  	  	    	}
  	  	    	else {
  	  	    		g.jsmessage("Null Error Message");
  	  	    	}
  	    	} 
  	    	else {
  	    		g.jsmessage("관리자 계정으로만 공지사항 정보 수정이 가능합니다.");
  	    	}
  	        
  	    	
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    if(viewName != null) {
  	        RequestDispatcher view = request.getRequestDispatcher(viewName);
		    view.forward(request, response);
  	    }
  	    else {
  	    	g.jsmessage("관리자 계정으로만 공지사항 정보 수정이 가능합니다."); 
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
		HttpSession session = request.getSession();
		String viewName = null;
		
		String id = (String)session.getAttribute("id");
		
	    //Parameter from HTML
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title"); 
	    String content = request.getParameter("content"); 
	    title = XssPreventer.escape(title); 
	    content = XssPreventer.escape(content);
	    
        Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //현재 날짜
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try {
  	    	if(id.equals("admin")) {
  	    		NoticeDTO noticedto = new NoticeDTO(num, title, content, null, modifydate);
  	    		NoticeDAO noticedao = new NoticeDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    		
  	    		int result = noticedao.updateBoard(noticedto);
  	    		
  	    		if(result != 0) {
  	    			session.removeAttribute("detailnoticelist");
  	    			viewName = "noticeboardlist.do?desc=0"; 
  	    		}
  	    		else {
  	    			g.jsmessage("Unknown Error Message");
  	    		}
  	    	}
  	    	else {
  	    		g.jsmessage("관리자 계정으로만 공지사항 정보 수정이 가능합니다.");
  	    	}
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    if(viewName != null) {
  	    	response.sendRedirect(viewName);
  	    }
  	    else {
  	    	g.jsmessage("공지사항 정보를 수정하는데 오류가 발생하였습니다.");
  	    }
	}

}
