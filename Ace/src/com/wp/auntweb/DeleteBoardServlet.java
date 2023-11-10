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

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.wp.auntweb.DAO.BoardDAO;

/**
 * Servlet implementation class DeleteBoardServlet
 */
@WebServlet("/deleteboardlist.do")
public class DeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoardServlet() {
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
		String id = (String)session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try {
			BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			if(id.equals("admin")) {
			    Map<String, String> boardlist = boarddao.getBoardListByNum(num, true);
			    if(boardlist != null) {
			    	session.setAttribute("detailboardlist", boardlist);
			    	viewName = "index.jsp?page=24"; 
			    }
			    else {
			    	g.jsmessage("Unknown Error Message");
			    }
			}
			else {
				g.jsmessage("������ �������θ� �ڷ�� ������ �����մϴ�."); 
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Global g = new Global(response);
		String viewName = null;
		
		String id = (String)session.getAttribute("id");
		
		//Parameters from HTML
		int num = Integer.parseInt(request.getParameter("num"));
		//String filename = request.getParameter("filename");
		
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    try {
  	    	if(id.equals("admin")) {
  	    		
  	    	    String os = System.getProperty("os.name"); //OS Check 
  	    	    String location = null;
  	    	    
  	    	    if(os.equals("Windows 10")) {
  	    	    	location = "C:\\Temp\\Board\\" + num; 
  	    	    }
  	    	    else if(os.equals("Linux")) {
  	    	    	location = "/mnt/hdd3/TextFiles/Board/" + num; 
  	    	    }
  	    	    else if(os.equals("Mac")) {
  	    	    	//�ƺ� ��� �������� 
  	    	    }
  	    		
  	    		File file = new File(location);
  	    		
  	    		if(file.exists()) { //������ �����ϸ� ���� �� ���� �� ���� ���� 
  	    			FileUtils.cleanDirectory(file); //���� �� ���� ���� 
  	                if(file.isDirectory()) {
  	                	file.delete();  //���� ���� 
  	                }
  	    		}
  	    		
  	    		BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    		
  	    		int result = boarddao.deleteBoard(num);
  	    		
  	    		if(result != 0) {
  	    			viewName = "totalboardlist.do";
  	    			session.removeAttribute("detailboardlist");
  	    		}
  	    		else {
  	    			g.jsmessage("Unknown Error Message");
  	    		}
  	    	}
  	    	else {
  	    		g.jsmessage("������ �������θ� �ڷ�� ������ �����մϴ�."); 
  	    	}
  	    }
  	    catch(Exception ex) {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    
  	    if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("�ڷ�� ������ �����ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
	}

}
