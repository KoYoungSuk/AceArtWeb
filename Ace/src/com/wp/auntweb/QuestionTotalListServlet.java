package com.wp.auntweb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.auntweb.DAO.QuestionDAO;
import com.wp.auntweb.DTO.QuestionDTO;

/**
 * Servlet implementation class QuestionTotalListServlet
 */
@WebServlet("/totalquestionlist.do")
public class QuestionTotalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionTotalListServlet() {
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
		String viewName = null;
		HttpSession session = request.getSession();
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    int page_count = 1; //���� ������ ��ȣ
  	    
  	    String page_count_str = request.getParameter("page_count");
  	    
  	    if(page_count_str != null) {
  	    	page_count = Integer.parseInt(page_count_str); 
  	    }
  	    
  	   
		try {
			QuestionDAO questiondao = new QuestionDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			int countnum = questiondao.getCountNum();
			
			List<QuestionDTO> totalquestionlist = questiondao.getTotalquestionList();

			
			if(totalquestionlist != null) {
				int pagenum = countnum / 5;  //�������� 5���� ������

				session.setAttribute("pagenum_question", pagenum + 1); //5���� ���� ������ ���� + 1 
				session.setAttribute("beginnumber_question", (page_count - 1) * 5); //���۹�ȣ
			    session.setAttribute("endnumber_question", ((page_count -1) * 5) + 4); //����ȣ 
				
				session.setAttribute("totalquestionlist", totalquestionlist);
				viewName = "index.jsp?page=33"; 
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
		else {
			g.jsmessage("���� ��ü ����Ʈ�� �ҷ����µ� ������ �߻��Ͽ����ϴ�.");
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
