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
 * Servlet implementation class DetailQuestionServlet
 */
@WebServlet("/detailquestion.do")
public class DetailQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailQuestionServlet() {
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
		HttpSession session = request.getSession();
		String viewName = null;
		
		String id = (String)session.getAttribute("id");  //현재 로그인한 ID 
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
				if(questionlist.get("access") != null) {
					if(questionlist.get("access").equals("secret")) { //비밀 질문 
						if(id != null) {
							if(questionlist.get("user").equals(id) || id.equals("admin")) {
								session.setAttribute("detailquestionlist", questionlist);
								session.setAttribute("user", questionlist.get("user"));
								viewName = "index.jsp?page=35"; 
							}
							else {
								g.jsmessage("비밀 질문입니다. (관리자 및 작성자만 확인가능) "); 
							}
						}
						else {
							g.jsmessage("비밀 질문입니다. (관리자 및 작성자만 확인가능) ");
						}
					}
				}
				else {
					session.setAttribute("detailquestionlist", questionlist);
					session.setAttribute("user", questionlist.get("user"));
					viewName = "index.jsp?page=35"; 
				}
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
		doGet(request, response);
	}

}
