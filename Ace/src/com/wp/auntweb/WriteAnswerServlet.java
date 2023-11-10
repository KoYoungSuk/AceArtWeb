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
import com.wp.auntweb.DAO.AnswerDAO;
import com.wp.auntweb.DTO.QuestionDTO;

/**
 * Servlet implementation class WriteAnswerServlet
 */
@WebServlet("/writeanswer.do")
public class WriteAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteAnswerServlet() {
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
		Global g = new Global(response);
		HttpSession session = request.getSession();
		String viewName = null;
		
		String id = (String)session.getAttribute("id");
		
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content"); 
		String access = request.getParameter("access");
		title = XssPreventer.escape(title);
		content = XssPreventer.escape(content);
		access = XssPreventer.escape(access);
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    Timestamp savedate = new Timestamp(System.currentTimeMillis()); //현재 날짜
  	  
		try {
			if(id.equals("admin")) {
				AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int num2 = answerdao.getMaxnum();
				
				num2 = num2 + 1; 
				
				QuestionDTO questiondto = new QuestionDTO(num2, qnum, title, content, access, id, savedate, null); 
				
				int result = answerdao.insertAnswer(questiondto);
				
				if(result != 0) {
					viewName = "totalquestionlist.do"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				g.jsmessage("관리자만 답변할 수 있습니다."); 
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
	}

}
