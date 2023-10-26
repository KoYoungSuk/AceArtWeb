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

import com.wp.auntweb.DAO.WorksDAO;

/**
 * Servlet implementation class DeleteWorksServlet
 */
@WebServlet("/deleteworks.do")
public class DeleteWorksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWorksServlet() {
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
			if(id.equals("admin")) {
				WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				Map<String, String> detailworkslist = worksdao.getWorksListByNum(num, true);
				
				if(detailworkslist != null) {
					session.setAttribute("detailworkslist", detailworkslist);
					viewName = "index.jsp?page=29"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				g.jsmessage("�����ڸ� ��ǰ ���� ������ �����մϴ�.");
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
  	    	g.jsmessage("��ǰ ������ �о���µ� ������ �߻��Ͽ����ϴ�. "); 
  	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Global g = new Global(response);
		String viewName = null; 
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try {
			if(id.equals("admin")) {
				int num = Integer.parseInt(request.getParameter("num"));
				
				System.out.println("num: " + num);
				
				String location = null;
				String os = System.getProperty("os.name");
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\Works\\" + num; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Pictures/" + num; 
				}
				else if(os.equals("Mac")) {
					
				}
				
				File file = new File(location);
				
				if(file.exists()) { //������ �����ϸ� ���� �� ���� �� ���� ���� 
  	    			FileUtils.cleanDirectory(file); //���� �� ���� ���� 
  	                if(file.isDirectory()) {
  	                	file.delete();  //���� ���� 
  	                }
  	    		}
				
				WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int result = worksdao.deleteWorks(num);
				
				if(result != 0) {
					session.removeAttribute("detailworkslist");
					viewName = "workslist.do?desc=0"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				g.jsmessage("�����ڸ� ��ǰ ���� ������ �����մϴ�.");
			}
		}
		catch(Exception ex) {
			
		    ex.printStackTrace(); 
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName); 
		}
		else {
			g.jsmessage("��ǰ ������ �����ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
	}

}
