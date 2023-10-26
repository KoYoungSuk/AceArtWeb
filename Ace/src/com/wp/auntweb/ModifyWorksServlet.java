package com.wp.auntweb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Enumeration;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.wp.auntweb.DAO.WorksDAO;
import com.wp.auntweb.DTO.WorksDTO;

/**
 * Servlet implementation class ModifyWorksServlet
 */
@WebServlet("/modifyworks.do")
public class ModifyWorksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyWorksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String viewName = null;
		Global g = new Global(response);
		
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
                WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
                
                Map<String, String> detailworkslist = worksdao.getWorksListByNum(num, false); 
				
                if(detailworkslist != null) {
                	session.setAttribute("detailworkslist", detailworkslist);
                	viewName = "index.jsp?page=28"; 
                }
                else {
                	g.jsmessage("Unknown Error Message");
                }
			}
			else {
				g.jsmessage("관리자로만 작품 정보 수정이 가능합니다.");
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
  	    	g.jsmessage("작품 정보를 읽어오는데 오류가 발생하였습니다. "); 
  	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String viewName = null;
		Global g = new Global(response);
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //현재 날짜
  	    
		try {
			if(id.equals("admin")) {
				int maxSize = 1024 * 1024 * 1024 * 5;
				
				String os = System.getProperty("os.name");
				String location = null;
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\Works\\"; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Pictures/"; 
				}
				else if(os.equals("Mac")) {
					
				}
				
				//일단 파일 업로드.  (파일 수정을 안했으면 업로드되지 않을 것이다.) 
				MultipartRequest multi = new MultipartRequest(request,
		 			      location,
						  maxSize,
						  "utf-8",
						  new DefaultFileRenamePolicy());
				
				int num = Integer.parseInt(multi.getParameter("num")); 
				String name = multi.getParameter("name");
				String description = multi.getParameter("description");
				String installdate = multi.getParameter("installdate");
				
			    Enumeration<?> files = multi.getFileNames();
				    
				String element = "";
				String filesystemName = "";
				    
				if(files.hasMoreElements()) {
				    element = (String)files.nextElement();
				    filesystemName = multi.getFilesystemName(element);
			    }
				
				String newlocation = "";
				
				//만약 파일을 수정하였을 경우. 
				if(filesystemName != null) {
					newlocation = location + num; 
					
					File file = new File(newlocation);
					
					if(file.exists()) {
						FileUtils.cleanDirectory(file); //폴더 내부에 있는 파일 지우기. 
					}
					
					if(os.equals("Windows 10")) {
						newlocation = newlocation + "\\" + filesystemName; 
					}
					else if(os.equals("Linux")) {
						newlocation = newlocation + "/" + filesystemName; 
					}
					else if(os.equals("Mac")) {
						
					}
					location = location + filesystemName;
					
					Path oldfile = Paths.get(location);
					Path newfile = Paths.get(newlocation);
					
					Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING); 
					
				}
				
				WorksDTO worksdto = new WorksDTO(num, name, filesystemName, installdate, null, modifydate, description);
				WorksDAO worksdao = new WorksDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int result = worksdao.updateWorks(worksdto);
				
				if(result != 0) {
					session.removeAttribute("detailworkslist");
					viewName = "workslist.do?desc=0"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				g.jsmessage("관리자만 작품 정보 수정이 가능합니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("작품 정보를 수정하는데 오류가 발생하였습니다.");
		}
	}

}
