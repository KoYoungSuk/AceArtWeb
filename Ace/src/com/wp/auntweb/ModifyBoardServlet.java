package com.wp.auntweb;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.wp.auntweb.DAO.BoardDAO;

/**
 * Servlet implementation class ModifyBoardServlet
 */
@WebServlet("/modifyboardlist.do")
public class ModifyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBoardServlet() {
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
		HttpSession session = request.getSession();
		String viewName = null;
		
		String id = (String)session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
		try {
			if(id.equals("admin")) {
				BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
				Map<String, String> boardlist = boarddao.getBoardListByNum(num, false);
				
				if(boardlist != null) {
					session.setAttribute("detailboardlist", boardlist);
					viewName = "index.jsp?page=25"; 
				}
			}
			else {
				g.jsmessage("관리자 계정으로만 자료실 정보를 수정할 수 있습니다."); 
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
			g.jsmessage("자료실 정보를 불러오는데 오류가 발생하였습니다.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Global g = new Global(response);
		HttpSession session = request.getSession();
		String viewName = null;
		
	    String id = (String)session.getAttribute("id"); 
	    int num = Integer.parseInt(request.getParameter("num"));
	    
	    
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
		try {
			if(id.equals("admin")) {
				int maxSize = 1024 * 1024 * 1024 * 5;
				
				String filename = request.getParameter("filename");
				
				String location = "C:\\Temp\\" + filename; //Windows
			    //String location = "/mnt/hdd3/TextFiles/" + filename;  //Linux
				
				File files = new File(location);
				
				if(files.exists()) {
					files.delete();  //이전 파일 삭제  
				}
				
				MultipartRequest multi = new MultipartRequest(request,
		 			      location,
						  maxSize,
						  "utf-8",
						  new DefaultFileRenamePolicy());
				
				String title = multi.getParameter("title");
				
				
			}
			else {
				g.jsmessage("관리자 계정으로만 자료실 정보를 수정할 수 있습니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
		
	}

}
