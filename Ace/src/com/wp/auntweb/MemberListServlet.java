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

import com.wp.auntweb.DAO.MemberDAO;
import com.wp.auntweb.DTO.MemberDTO;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/totalmemberlist.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
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
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
		
		try {
			if(id != null) {
				if(id.equals("admin")) {
					 MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
					    
					 List<MemberDTO> totalmemberlist = memberdao.GetTotalMemberList();
					    
					 if(totalmemberlist != null) {
					    	viewName = "index.jsp?page=21";
					    	session.setAttribute("totalmemberlist", totalmemberlist); 
					 }
					 else {
						 g.jsmessage("Unknown Error Message");
					 }
				}
				else {
					session.invalidate(); 
					g.jsmessage("관리자 계정으로만 사용이 가능합니다."); 
				}
			}
			else {
				session.invalidate();
				g.jsmessage("Null Error");
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
			g.jsmessage("전체 회원정보를 조회하는데 오류가 발생하였습니다.");
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
