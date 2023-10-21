package com.wp.auntweb;

import java.util.ArrayList;
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
		Boolean desc_bool = false;
		
		String id = (String)session.getAttribute("id"); 
		String access = null;
		
		int desc = Integer.parseInt(request.getParameter("desc")); 
		
		if(desc == 0) {
			desc_bool = false;
		}
		else {
			desc_bool = true;
		}
		
		if(id != null) { //로그인되어 있을 때 
			if(id.equals("admin")) {
				access = "admin"; //관리자 
			}
			else {
				access = "member"; //회원 
			}
		}
		else {
			access = "anonymous"; //비회원 
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
			
			List<BoardDTO> totalboardlist = boarddao.getTotalBoardList(desc_bool); 
			List<BoardDTO> newtotalboardlist = new ArrayList<BoardDTO>(); 
			
			if(totalboardlist != null) {
				for(BoardDTO boarddto : totalboardlist) {
					if(access.equals("admin")) { //관리자로 로그인하였으면 전체 
						newtotalboardlist.add(boarddto); 
					}
					else if(access.equals("member")) {
						if(!boarddto.getAccess().equals("admin")) { //회원 모드이면 관리자가 아닌 것들만 볼 수 있게 한다. 
							newtotalboardlist.add(boarddto); 
						}
					}
					else {
						if(boarddto.getAccess().equals("anonymous")) { //비회원 모드이면 비회원 모드인것들만 볼 수 있게 한다. 
							newtotalboardlist.add(boarddto); 
						}
					}
				}
		   }
		   else {
				g.jsmessage("Null Error Message");
		   }
			
		   if(newtotalboardlist != null) {
			   session.setAttribute("totalboardlist", newtotalboardlist);
			   viewName = "index.jsp?page=7"; 
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
