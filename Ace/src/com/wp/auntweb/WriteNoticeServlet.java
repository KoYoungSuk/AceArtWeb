package com.wp.auntweb;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class WriteNoticeServlet
 */
@WebServlet("/writenotice.do")
public class WriteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Global g = new Global(response);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String viewName = null; 
		
		//Parameter from HTML
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Timestamp joindate = new Timestamp(System.currentTimeMillis()); //현재 날짜
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try {
  	    	if(id != null) {
  	    		if(id.equals("admin")) {
  	  	    		title = XssPreventer.escape(title); 
  	  	    		content = XssPreventer.escape(content); //Lucy XSS Filter 적용(크로스사이트 스크립팅 방지: 스크립트 태그를 HTML로 인식못하게 변환) 
  	  	    	   
  	  	    		NoticeDAO noticedao = new NoticeDAO(JDBC_Driver, db_url, db_id, db_pw); 
  	  	  	    		
  	  	  	    	int maxnumber = noticedao.getMaxBoardNumber();
  	  	  	    		
  	  	  	        NoticeDTO noticedto = new NoticeDTO(maxnumber + 1, title, content, joindate, null);
  	  	  	            
  	  	  	        int result = noticedao.insertBoard(noticedto);
  	  	  	            
  	  	  	        if(result != 0) {
  	  	  	            viewName = "noticeboardlist.do?desc=0"; 
  	  	  	        }
  	  	  	        else {
  	  	  	            g.jsmessage("Unknown Error Message");
  	  	  	        }
  	  	    	}
  	  	    	else {
  	  	    		g.jsmessage("Administrator Only!");
  	  	    	}
  	    	}
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage()); 
  	    }
		
  	    if(viewName != null) {
  	    	response.sendRedirect(viewName);
  	    }
  	    else {
  	    	g.jsmessage("공지사항을 추가하는데 오류가 발생하였습니다.");
  	    }
	}

}
