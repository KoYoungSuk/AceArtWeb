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

import com.wp.auntweb.DAO.NoticeDAO;
import com.wp.auntweb.DTO.NoticeDTO;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/noticeboardlist.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
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
		Global g = new Global(response);
		String viewName = null;
		
		
		String page_count_str = request.getParameter("page_count");
		int page_count = 0;
		
		if(page_count_str == null) {
			page_count = 1; 
		}
		else {
			page_count = Integer.parseInt(page_count_str); 
		}
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    try {
  	    	NoticeDAO noticedao = new NoticeDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    	
  	    	int countnum = noticedao.getCountBoardNumber();
  	    	int pagenum = countnum / 10;
  	    	int pagenum_rest = countnum % 10;
  	    	
  	    	List<NoticeDTO> noticelist = noticedao.getBoardList(false); 
  	    	
  	    	if(noticelist != null) {
  	    		session.setAttribute("pagenum_notice", pagenum + 1); //10개씩 나눈 페이지 개수 + 1 
				session.setAttribute("beginnumber_notice", (page_count - 1) * 10); //시작번호
			    session.setAttribute("endnumber_notice", ((page_count -1) * 5) + 10); //끝번호 
  	    		session.setAttribute("noticeboardlist", noticelist);
  	    		viewName = "index.jsp?page=6"; 
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
  	    	g.jsmessage("공지사항 정보를 읽어오는데 오류가 발생하였습니다. "); 
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
