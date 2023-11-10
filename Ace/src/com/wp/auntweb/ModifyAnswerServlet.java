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
import com.wp.auntweb.DAO.AnswerDAO;
import com.wp.auntweb.DTO.QuestionDTO;

/**
 * Servlet implementation class ModifyAnswerServlet
 */
@WebServlet("/modifyanswer.do")
public class ModifyAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAnswerServlet() {
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
		String viewName = null;
		HttpSession session = request.getSession();
		Global g = new Global(response);
		
		String id = (String)session.getAttribute("id"); 
		int num = Integer.parseInt(request.getParameter("num")); 
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    
		try {
			if(id.equals("admin")) {
				AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				Map<String, String> detailanswerlist = answerdao.getAnswerList(num, false); 
				
				if(detailanswerlist != null) {
					session.setAttribute("detailanswerlist", detailanswerlist); 
					viewName = "index.jsp?page=40"; 
				}
			}
			else {
				g.jsmessage("�����ڸ� �亯 ������ �����մϴ�.");
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
		Global g = new Global(response);
		String viewName = null;
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		int num = Integer.parseInt(request.getParameter("num")); 
		String content = request.getParameter("content");
		content = XssPreventer.escape(content); 
		
		Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //���� ��¥
		 
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try {
			if(id.equals("admin")) {
				AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw);
				QuestionDTO questiondto = new QuestionDTO(num, 0, null, content, null, id, null, modifydate);
				
			    int result = answerdao.updateAnswer(questiondto);
			    
			    if(result != 0) {
			    	session.removeAttribute("detailanswerlist");
			    	viewName = "totalquestionlist.do"; 
			    }
			    else {
			    	g.jsmessage("Unknown Error Message");
			    }
			}
			else {
				g.jsmessage("�����ڸ� �亯 ������ �����մϴ�.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("�亯 ������ �����ϴµ� ������ �߻��Ͽ����ϴ�."); 
		}
	}

}
