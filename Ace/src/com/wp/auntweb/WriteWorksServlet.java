package com.wp.auntweb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.wp.auntweb.DAO.WorksDAO;
import com.wp.auntweb.DTO.WorksDTO;

/**
 * Servlet implementation class WriteWorksServlet
 */
@WebServlet("/writeworks.do")
public class WriteWorksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteWorksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Global g = new Global(response);
		g.errorcode(403); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String viewName = null;
		HttpSession session = request.getSession();
		Global g = new Global(response); 
		
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
				Timestamp savedate = new Timestamp(System.currentTimeMillis()); //���� ��¥
				
				int maxSize = 1024 * 1024 * 1024 * 5;
				
				String os = System.getProperty("os.name"); 
				String location = null;
				
                WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int maxnum = worksdao.getMaxNum();
				maxnum = maxnum + 1;
				if(os.equals("Windows 10")){
					location = "C:\\Temp\\Works\\" + maxnum; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Pictures/" + maxnum; 
				}
				else if(os.equals("Mac")) {
					//�ƺϺ��� �ϴ� ����... 
				}
				
				Path directoryPath = Paths.get(location); //���͸� ��� ���� 
				
				if(!Files.exists(directoryPath)) 
				{
					Files.createDirectory(directoryPath); //���͸� ���� 
				}
				
				if(os.equals("Windows 10")) {
					location = location + "\\"; 
				}
				else if(os.equals("Linux")) {
					location = location + "/"; 
				}
				else if(os.equals("Mac")) {
				    //�ƺ� ���ʹ�.... 
				}
				
				MultipartRequest multi = new MultipartRequest(request,
		 			      location,
						  maxSize,
						  "utf-8",
						  new DefaultFileRenamePolicy());
			   
				String name = multi.getParameter("name");
				String description = multi.getParameter("description");
				String installdate = multi.getParameter("installdate");
				
				name = XssPreventer.escape(name); 
				description = XssPreventer.escape(description);
				installdate = XssPreventer.escape(installdate); 
				
			    Enumeration<?> files = multi.getFileNames();
				    
				String element = "";
				String filesystemName = "";
				    
				if(files.hasMoreElements()) {
				    element = (String)files.nextElement();
				    filesystemName = multi.getFilesystemName(element);
			    }
			
				WorksDTO worksdto = new WorksDTO(maxnum, name, filesystemName, installdate, savedate, null, description);
				
				int result = worksdao.insertWorks(worksdto);
				
				if(result != 0) {
					viewName = "workslist.do"; 
				}
				else {
					g.jsmessage("Unknown Error Message"); 
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("�����ڸ� ��ǰ ����� �����մϴ�.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getStackTrace().toString()); 
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("��ǰ ���� ����� �ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
	}

}
