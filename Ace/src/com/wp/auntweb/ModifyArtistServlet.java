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

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.wp.auntweb.DAO.ArtistDAO;
import com.wp.auntweb.DTO.ArtistDTO;

/**
 * Servlet implementation class ModifyArtistServlet
 */
@WebServlet("/modifyartist.do")
public class ModifyArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyArtistServlet() {
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
		String viewName = null;
		Global g = new Global(response);
		HttpSession session = request.getSession();
		
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
			if(id != null) {
				if(id.equals("admin")) {
					ArtistDAO artistdao = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
					
					Map<String, String> artistlist = artistdao.getArtistlistByNum(num, false);
					
					if(artistlist != null) {
						String num_new = artistlist.get("num");
						if(num_new != null) {
							session.setAttribute("artistlist", artistlist); 
							viewName = "index.jsp?page=32"; 
						}
					}
					else {
						g.jsmessage("Unknown Error Message"); 
					}
				}
				else {
					session.invalidate(); 
					g.jsmessage("관리자만 작가 정보 수정이 가능합니다.");
				}
			}
			else {
				session.invalidate(); 
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
  	    	g.jsmessage("작가 정보를 읽어오는데 오류가 발생하였습니다. "); 
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
			if(id != null) {
				if(id.equals("admin")) {
					 
					 String location = null;
					 String location2_1 = null;
					 String location2_2 = null; 
					 
					 int maxSize = 1024 * 1024 * 1024 * 5; 
					 ArtistDAO artistdao = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
					 
					 String os = System.getProperty("os.name");
					 
					 if(os.equals("Windows 10")) {
						 location = "C:\\Temp\\Artist\\"; 
					 }
					 else if(os.equals("Linux")){
						 location = "/mnt/hdd3/TextFiles/Artist/"; 
					 }
					 else if(os.equals("Mac")) {
						 
					 }
					 
					 //일단 파일부터 먼저 업로드 
				     MultipartRequest multi = new MultipartRequest(
								request, 
								location, 
							    maxSize, 
								"UTF-8", 
								new DefaultFileRenamePolicy());
				     
				    Enumeration<?> files = multi.getFileNames();
					    
					String element = "";
					String filesystemName = "";
					
					String[] filesystemName_list = new String[2]; 
					
					int i = 0;
					   
					while(files.hasMoreElements()) {
						   element = (String)files.nextElement();
						   filesystemName = multi.getFilesystemName(element);
						   filesystemName_list[i] = filesystemName;
						   i++; 
				   }
				   //filesystemName_list[0]: WORK2 
				   //filesystemName_list[1]: WORK1 
				  
				   String prelocation = null; //이전 경로(삭제해야할 파일) 
				   String newlocation = null; 
				   String newlocation2_1 = null; 
				   String newlocation2_2 = null; 
				   
				   int num = Integer.parseInt(multi.getParameter("num"));
				   String name = multi.getParameter("name");
				   String career = multi.getParameter("career"); 
				   name = XssPreventer.escape(name); 
				   career = XssPreventer.escape(career); 
				   String old_work1 = multi.getParameter("oldFile1");
				   String old_work2 = multi.getParameter("oldFile2"); 
						  
				   if(filesystemName_list[0] != null && filesystemName_list[1] != null) {
					   newlocation = location + num;
					   
					   File file = new File(newlocation); 
					   if(file.exists()) {
							FileUtils.cleanDirectory(file); //폴더 내부에 있는 파일 지우기. 
						}
						
						if(os.equals("Windows 10")) {
							newlocation2_1 = newlocation + "\\" + filesystemName_list[0];  
							newlocation2_2 = newlocation + "\\" + filesystemName_list[1]; 
						}
						else if(os.equals("Linux")) {
							newlocation2_1 = newlocation + "/" + filesystemName_list[0]; 
							newlocation2_2 = newlocation + "/" + filesystemName_list[1]; 
						}
						else if(os.equals("Mac")) {
							newlocation2_1 = newlocation + "/" + filesystemName_list[0]; 
							newlocation2_2 = newlocation + "/" + filesystemName_list[1]; 
						}
						location2_1 = location + filesystemName_list[0]; 
						location2_2 = location + filesystemName_list[1]; 
						
						Path oldfile = Paths.get(location2_1);
						Path oldfile2 = Paths.get(location2_2); 
						Path newfile = Paths.get(newlocation2_1);
						Path newfile2 = Paths.get(newlocation2_2); 
						
						Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING); 
						Files.move(oldfile2, newfile2, StandardCopyOption.REPLACE_EXISTING); 
				   }
				   else if(filesystemName_list[0] != null) { //두번째 파일만 수정했을 경우 
					    newlocation = location + num;
					    
					    
						if(os.equals("Windows 10")) {
							prelocation = newlocation + "\\" + old_work2; 
							newlocation = newlocation + "\\" + filesystemName_list[0];  
						}
						else if(os.equals("Linux")) {
							prelocation = newlocation + "/" + old_work2; 
							newlocation = newlocation + "/" + filesystemName_list[0]; 
						}
						else if(os.equals("Mac")) {
							prelocation = newlocation + "/" + old_work2; 
							newlocation = newlocation + "/" + filesystemName_list[0]; 
						}
						
						File file = new File(prelocation);
						
						if(file.exists()) {
							file.delete(); //이전 파일 삭제 
						}
						
						location = location + filesystemName_list[0];
						
						Path oldfile = Paths.get(location);
						Path newfile = Paths.get(newlocation);
						
						Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING); 
				   }
				   else if(filesystemName_list[1] != null) { //첫번째 파일만 수정했을 경우 
					   newlocation = location + num;
					   
						if(os.equals("Windows 10")) {
							prelocation = newlocation + "\\" + old_work1; 
							newlocation = newlocation + "\\" + filesystemName_list[1];  
						}
						else if(os.equals("Linux")) {
							prelocation = newlocation + "/" + old_work1;	
							newlocation = newlocation + "/" + filesystemName_list[1]; 
						}
						else if(os.equals("Mac")) {
							prelocation = newlocation + "/" + old_work1;	
							newlocation = newlocation + "/" + filesystemName_list[1];
						}
						
						File file = new File(prelocation);
						if(file.exists()) {
							file.delete(); //이전 파일 삭제 
						}
						
						location = location + filesystemName_list[1];
						
						Path oldfile = Paths.get(location);
						Path newfile = Paths.get(newlocation);
						
						Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING); 
				   }
				   
				   
				   ArtistDTO artistdto = new ArtistDTO(num, name, career, filesystemName_list[1], filesystemName_list[0], null, modifydate);
				   
				   int result = artistdao.updateArtist(artistdto);
				   
				   if(result != 0) {
					   session.removeAttribute("artistlist");
					   viewName = "totalartistlist.do"; 
				   }
				   else {
					   g.jsmessage("Unknown Error Message");
				   }
				}
				else {
					session.invalidate(); 
					g.jsmessage("관리자만 작가 정보 수정이 가능합니다.");
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("Null Error Message");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("작가 정보를 수정하는데 오류가 발생하였습니다.");
		}
	}

}
