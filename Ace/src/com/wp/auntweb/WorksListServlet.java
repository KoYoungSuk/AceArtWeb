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

import com.wp.auntweb.DAO.WorksDAO;
import com.wp.auntweb.DTO.WorksDTO;

/**
 * Servlet implementation class WorksListServlet
 */
@WebServlet("/workslist.do")
public class WorksListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Global g = new Global(response);
		String viewName = null;
		HttpSession session = request.getSession();
		
		String page_count_str = request.getParameter("page_count");
		
		int page_count = 0;
		
		if(page_count_str != null) {
			page_count = Integer.parseInt(page_count_str); 
		}
		else {
			page_count = 1; 
		}
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    
		try {
			WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			List<WorksDTO> workslist = worksdao.getTotalWorksList(false);
			int maxnum = worksdao.getCountnum();
			
			int page_num = maxnum / 10; //10���� ������. 
			
			if(workslist != null) {
				viewName = "index.jsp?page=5";
				session.setAttribute("workslist", workslist); 
				session.setAttribute("pagenum_works", page_num + 1); //5���� ���� ������ ���� + 1 
				session.setAttribute("beginnumber_works", (page_count - 1) * 5); //���۹�ȣ
			    session.setAttribute("endnumber_works", ((page_count -1) * 5) + 4); //����ȣ 
				
			}
			else {
				g.jsmessage("Unknown Error Message");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getLocalizedMessage());
		}
		
		if(viewName != null) {
  	  	    RequestDispatcher view = request.getRequestDispatcher(viewName);
 		    view.forward(request, response);
  	    }
  	    else {
  	    	g.jsmessage("��ǰ ������ �о���µ� ������ �߻��Ͽ����ϴ�. "); 
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
