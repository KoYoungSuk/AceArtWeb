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

import com.wp.auntweb.DAO.QuestionDAO;

/**
 * Servlet implementation class DeleteQuestionServlet
 */
@WebServlet("/deletequestion.do")
public class DeleteQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestionServlet() {
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
		int num = Integer.parseInt(request.getParameter("num"));
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
		try {
			QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			Map<String, String> questionlist = questiondao.getDetailQuestion(num, true);
			
			if(questionlist != null) {
				if(questionlist.get("user").equals(id)) {
					session.setAttribute("detailquestionlist", questionlist); 
					viewName = "index.jsp?page=37"; 
				}
				else {
					g.jsmessage("작성자만 질문 정보 삭제가 가능합니다.");
				}
			}
			else {
				g.jsmessage("Unknown Error Message");
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
		String viewName = null;
		Global g = new Global(response);
		
		int num = Integer.parseInt(request.getParameter("num"));
		String id = (String)session.getAttribute("id");
		String user = request.getParameter("user");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
		try {
			QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			if(id.equals(user)) {
				int result = questiondao.deleteQuestion(num);
				
				if(result != 0) {
					session.removeAttribute("detailquestionlist");
					viewName = "totalquestionlist.do"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("작성자만 질문정보 수정이 가능합니다.");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace(); 
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName); 
		}
	}

}
