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

import com.wp.auntweb.DAO.ArtistDAO;
import com.wp.auntweb.DTO.ArtistDTO;

/**
 * Servlet implementation class ArtistListServlet
 */
@WebServlet("/totalartistlist.do")
public class ArtistListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistListServlet() {
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
			ArtistDAO artistDAO = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
			List<ArtistDTO> totalartistlist = artistDAO.getTotalartistList(true);
			
			int countnum = artistDAO.getCountnum();
			int pagenum_artist = countnum / 5;
			
			if(totalartistlist != null) {
			    session.setAttribute("totalartistlist", totalartistlist);
				session.setAttribute("pagenum_artist", pagenum_artist + 1); //5개씩 나눈 페이지 개수 + 1 
				session.setAttribute("beginnumber_artist", (page_count - 1) * 5); //시작번호
			    session.setAttribute("endnumber_artist", ((page_count -1) * 5) + 4); //끝번호 
			    viewName = "index.jsp?page=15"; 
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
  	    	g.jsmessage("작가 정보를 읽어오는데 오류가 발생하였습니다. "); 
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
