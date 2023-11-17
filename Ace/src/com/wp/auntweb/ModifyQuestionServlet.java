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
import com.wp.auntweb.DAO.QuestionDAO;
import com.wp.auntweb.DTO.QuestionDTO;

/**
 * Servlet implementation class ModifyQuestionServlet
 */
@WebServlet("/modifyquestion.do")
public class ModifyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyQuestionServlet() {
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
		
		String id = (String)session.getAttribute("id"); //현재 로그인한 아이디
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

			Map<String, String> questionlist = questiondao.getDetailQuestion(num, false);
			
			if(questionlist != null) {
				if(questionlist.get("user").equals(id)) {
					 if(questionlist.get("num") != null) {
						 session.setAttribute("detailquestionlist", questionlist);
						 viewName = "index.jsp?page=36"; 
					 }
				}
				else {
					g.jsmessage("작성자만 수정이 가능합니다.");
				}
			}
			//index.jsp?page=36 
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
		
		if(viewName != null) {
			RequestDispatcher view = request.getRequestDispatcher(viewName);
	 		view.forward(request, response);
		}
		else {
			g.jsmessage("질문 정보를 불러오는데 오류가 발생하였습니다.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String viewName = null;
		Global g = new Global(response);
				
		String id = (String)session.getAttribute("id"); //현재 로그인한 아이디
		int num = Integer.parseInt(request.getParameter("num"));
				
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String access = request.getParameter("access");
		String user = request.getParameter("user"); 
		
		title = XssPreventer.escape(title);
		content = XssPreventer.escape(content);
		access = XssPreventer.escape(access);
	    user = XssPreventer.escape(user);
	    
		Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //현재 날짜
				
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
		String JDBC_Driver = application.getInitParameter("jdbc_driver");
		String db_url = application.getInitParameter("db_url");
		String db_id = application.getInitParameter("db_userid");
		String db_pw = application.getInitParameter("db_password");
		//END - 데이터베이스 연결 준비 (web.xml)
		  	    
		try {
		   QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
		   QuestionDTO questiondto = new QuestionDTO(num, title, content, id, access, null, modifydate);
		   
		   int result = 0;
		   
		   if(id.equals(user)) {
			   result = questiondao.updateQuestion(questiondto);
		   }
		   else {
			   g.jsmessage("작성자만 질문정보 수정이 가능합니다.");
		   }
					
		   if(result != 0) {
			  session.removeAttribute("detailquestionlist"); 
			  viewName = "totalquestionlist.do"; 
		   }
		   else {
			  g.jsmessage("Unknown Error Message");
		   }
					//index.jsp?page=36 
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
				
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
	}

}
