package com.wp.auntweb;


import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.auntweb.DAO.BoardDAO;
import com.wp.auntweb.DTO.BoardDTO;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/totalboardlist.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
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
			BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			int countnum = boarddao.getCountBoard();
			int pagenum = countnum / 10;
			int pagenum_rest = countnum % 10;
			
			List<BoardDTO> totalboardlist = boarddao.getTotalBoardList(false); 
			
			if(totalboardlist != null) {
				   session.setAttribute("totalboardlist", totalboardlist);
				   session.setAttribute("pagenum_board", pagenum + 1); //5개씩 나눈 페이지 개수 + 1 
				   session.setAttribute("beginnumber_board", (page_count - 1) * 5); //시작번호
				   if(page_count == pagenum + 1) { //마지막 페이지일때 
						session.setAttribute("endnumber_board", ((page_count -1) * 5) + 4 + pagenum_rest); //끝번호 (마지막 페이지일때 나머지를 더한다.) 
				   }
				   else {
						session.setAttribute("endnumber_board", ((page_count -1) * 5) + 4); //끝번호 
				   }
					
				   viewName = "index.jsp?page=7"; 
		    }
		    else {
				g.jsmessage("Null Error Message");
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
  	    	g.jsmessage("자료실 정보를 읽어오는데 오류가 발생하였습니다. "); 
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
