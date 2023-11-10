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
import com.wp.auntweb.DAO.QuestionDAO;

/**
 * Servlet implementation class DetailAnswerServlet
 */
@WebServlet("/detailanswer.do")
public class DetailAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailAnswerServlet() {
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
		Global g = new Global(response);
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num")); 
        String id = (String)session.getAttribute("id");  //���� �α����� ���̵� 
        
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    try {
  	    	AnswerDAO answerdao = new AnswerDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    	QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw); 
  	    	
  	    	Map<String, String> detailanswerlist = answerdao.getAnswerList(num, true); 
  	    	Map<String, String> detailquestionlist = questiondao.getDetailQuestion(Integer.parseInt(detailanswerlist.get("q_num")), false); 
  	    	
  	    	String user = detailquestionlist.get("user"); 
  	    	
  	    	if(detailanswerlist != null) {
  	    		if(detailanswerlist.get("access") != null) {
  	    			if(detailanswerlist.get("access").equals("secret")) {
  	    				if(id.equals(user) || id.equals("admin")) {
  	    					viewName = "index.jsp?page=39";
  	  	    				session.setAttribute("detailanswerlist", detailanswerlist); 
  	    				}
  	    				else {
  	    					g.jsmessage("��� ��忡���� �ۼ��� �� �����ڸ� �亯�� Ȯ�� �����մϴ�.");
  	    				}
  	    			}
  	    		}
  	    		else {
  	    			viewName = "index.jsp?page=39";
  	    			session.setAttribute("detailanswerlist", detailanswerlist); 
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
