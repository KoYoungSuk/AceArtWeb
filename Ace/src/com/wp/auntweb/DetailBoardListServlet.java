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

import com.wp.auntweb.DAO.BoardDAO;

/**
 * Servlet implementation class DetailBoardListServlet
 */
@WebServlet("/detailboardlist.do")
public class DetailBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailBoardListServlet() {
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
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    String id = (String)session.getAttribute("id");
  	    
  	    String access = null;
  	    
  	    if(id != null) { //�α��� 
  	    	if(id.equals("admin")) { //������ 
  	    		access = "admin"; 
  	    	}
  	    	else { //ȸ�� 
  	    		access = "member"; 
  	    	}
  	    }
  	    else { //��ȸ�� 
  	    	access = "anonymous"; 
  	    }
  	    
  	    System.out.println("access: " + access);
  	    System.out.println("num: " + num); 
  	    
		try {
			BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
			Map<String, String> boardlist = boarddao.getBoardListByNum(num, true);
			
			if(boardlist != null) {
				if(access.equals("admin")) { //�����ڴ� ��ü 
					session.setAttribute("detailboardlist", boardlist); 
					viewName = "index.jsp?page=23";  
				}
				else if(access.equals("member")){
					if(!boardlist.get("access").equals("admin")) { 
						session.setAttribute("detailboardlist", boardlist);
						viewName = "index.jsp?page=23"; 
					}
				}
				else if(access.equals("anonymous")){
				   if(boardlist.get("access").equals(access)) {
					   session.setAttribute("detailboardlist", boardlist);
					   viewName = "index.jsp?page=23"; 
				   }
			    }
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
			g.jsmessage("�ڷ�� ������ �ҷ����µ� ������ �߻��Ͽ����ϴ�.");
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
