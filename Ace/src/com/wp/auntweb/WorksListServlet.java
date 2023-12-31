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
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    
		try {
			WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			List<WorksDTO> workslist = worksdao.getTotalWorksList(false);
			int maxnum = worksdao.getCountnum();
			
			int page_num = maxnum / 10; //10개씩 나눈다. 
			
			if(workslist != null) {
				viewName = "index.jsp?page=5";
				session.setAttribute("workslist", workslist); 
				session.setAttribute("pagenum_works", page_num + 1); //5개씩 나눈 페이지 개수 + 1 
				session.setAttribute("beginnumber_works", (page_count - 1) * 5); //시작번호
			    session.setAttribute("endnumber_works", ((page_count -1) * 5) + 4); //끝번호 
				
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
  	    	g.jsmessage("작품 정보를 읽어오는데 오류가 발생하였습니다. "); 
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
