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

import com.wp.auntweb.DAO.AnswerDAO;

/**
 * Servlet implementation class DeleteAnswerServlet
 */
@WebServlet("/deleteanswer.do")
public class DeleteAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnswerServlet() {
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
		Global g = new Global(response);
		String viewName = null;
		
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
				AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw); 
				
				Map<String, String> detailanswerlist = answerdao.getAnswerList(num, true);
				
				if(detailanswerlist != null) {
					session.setAttribute("detailanswerlist", detailanswerlist);
					viewName = "index.jsp?page=41"; 
				}
				else {
					g.jsmessage("Unknown Error Message"); 
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("관리자만 답변 삭제가 가능합니다."); 
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
		
		if(viewName != null) {
			RequestDispatcher view = request.getRequestDispatcher(viewName);
	 		view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Global g = new Global(response);
		String viewName = null;
		
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
  	    		AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    		
  	    		int result = answerdao.deleteAnswer(num);
  	    		
  	    		if(result != 0) {
  	    			session.removeAttribute("detailanswerlist");
  	    			viewName = "totalquestionlist.do"; 
  	    		}
  	    		else {
  	    			g.jsmessage("Unknown Error Message"); 
  	    		}
  	    	}
  	    	else {
  	    		session.invalidate(); 
  	    		g.jsmessage("관리자만 답변 삭제가 가능합니다."); 
  	    	}
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage()); 
  	    }
  	    
  	    if(viewName != null) {
  	    	response.sendRedirect(viewName); 
  	    }
  	    else {
  	    	g.jsmessage("답변 정보를 삭제하는데 오류가 발생하였습니다."); 
  	    }
	}

}
