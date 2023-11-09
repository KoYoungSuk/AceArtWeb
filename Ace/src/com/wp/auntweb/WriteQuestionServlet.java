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
import com.wp.auntweb.DAO.QuestionDAO;
import com.wp.auntweb.DTO.QuestionDTO;

/**
 * Servlet implementation class WriteQuestionServlet
 */
@WebServlet("/writequestion.do")
public class WriteQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteQuestionServlet() {
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
		String viewName = null;
		
		String id = (String)session.getAttribute("id");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content"); 
		String access = request.getParameter("access");
		
		title = XssPreventer.escape(title);
		content = XssPreventer.escape(content);
		access = XssPreventer.escape(access);
		
		Timestamp savedate = new Timestamp(System.currentTimeMillis()); //현재 날짜
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    
		try {
			if(id != null) {
				QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
				int maxnum = questiondao.getMaxNum();
				
			    QuestionDTO questiondto = new QuestionDTO(maxnum + 1, title, content, id, access, savedate, null);
			    
			    int result = questiondao.insertQuestion(questiondto);
			    
			    if(result != 0) {
			    	viewName = "totalquestionlist.do"; 
			    }
			    else{
			       g.jsmessage("Unknown Error Message"); 
			    }
			}
			else {
				g.jsmessage("로그인한 사용자만 질문하기 기능을 사용할 수 있습니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName); 
		}
		else {
			g.jsmessage("질문 정보를 등록하는데 오류가 발생하였습니다.");
		}
	}

}
