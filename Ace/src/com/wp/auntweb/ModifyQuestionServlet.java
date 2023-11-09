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
		
		String id = (String)session.getAttribute("id"); //���� �α����� ���̵�
		int num = Integer.parseInt(request.getParameter("num"));

		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try {
		    QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);

			Map<String, String> questionlist = questiondao.getDetailQuestion(num, false);
			
			if(questionlist != null) {
				if(questionlist.get("user").equals(id)) {
					 session.setAttribute("detailquestionlist", questionlist);
					 viewName = "index.jsp?page=36"; 
				}
				else {
					g.jsmessage("�ۼ��ڸ� ������ �����մϴ�.");
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
			g.jsmessage("���� ������ �ҷ����µ� ������ �߻��Ͽ����ϴ�.");
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
				
		String id = (String)session.getAttribute("id"); //���� �α����� ���̵�
		int num = Integer.parseInt(request.getParameter("num"));
				
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String access = request.getParameter("access");
		String user = request.getParameter("user"); 
		
		title = XssPreventer.escape(title);
		content = XssPreventer.escape(content);
		access = XssPreventer.escape(access);
	    user = XssPreventer.escape(user);
	    
		Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //���� ��¥
				
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
		String JDBC_Driver = application.getInitParameter("jdbc_driver");
		String db_url = application.getInitParameter("db_url");
		String db_id = application.getInitParameter("db_userid");
		String db_pw = application.getInitParameter("db_password");
		//END - �����ͺ��̽� ���� �غ� (web.xml)
		  	    
		try {
		   QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
		   QuestionDTO questiondto = new QuestionDTO(num, title, content, id, access, null, modifydate);
		   
		   int result = 0;
		   
		   if(id.equals(user)) {
			   result = questiondao.updateQuestion(questiondto);
		   }
		   else {
			   g.jsmessage("�ۼ��ڸ� �������� ������ �����մϴ�.");
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
