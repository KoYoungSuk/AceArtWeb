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
import com.wp.auntweb.DAO.BoardDAO;
import com.wp.auntweb.DTO.BoardDTO;

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
				else {
					g.jsmessage("Unknown Error Message");
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
	    
	    
	    
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    String os = System.getProperty("os.name"); //OS Check 
        String location = null; 
        
  	    if(os.equals("Windows 10")) {
  	    	location = "C:\\Temp\\Board\\"; 
  	    }
  	    else if(os.equals("Linux")) {
  	    	location = "/mnt/hdd3/TextFiles/Board/"; 
  	    }
  	    else if(os.equals("Mac")) {
  	    	//맥북사면 코드 수정예정 
  	    }
  	   
		try {
			if(id.equals("admin")) {
				
				int maxSize = 1024 * 1024 * 1024 * 5;
				
				//먼저 파일 업로드 
				MultipartRequest multi = new MultipartRequest(request,
		 			      location,
						  maxSize,
						  "utf-8",
						  new DefaultFileRenamePolicy());
				
				String title = multi.getParameter("title");
				int num = Integer.parseInt(multi.getParameter("num")); 
				String content = multi.getParameter("content");
				title = XssPreventer.escape(title); 
				content = XssPreventer.escape(content);
				
				Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //현재 날짜
				
				Enumeration<?> files = multi.getFileNames();
				    
				String element = "";
				String filesystemName = "";
				 
				if(files.hasMoreElements()) {
				      element = (String)files.nextElement();
				      filesystemName = multi.getFilesystemName(element); //서버에 업로드한 파일명 
				}
				 
			    String newlocation = null;
			    
			    if(filesystemName != null) { //파일을 수정했을 경우 그 수정한 파일이 업로드되는데, 그 경로를 바꿔줘야 한다. 
			    	 //번호에 맞춰서 경로를 바꿔준다. 
			    	 
			    	 newlocation = location + num; 

			    	 File file = new File(newlocation);
			    	 
			    	 if(file.exists()) {
			    		 FileUtils.cleanDirectory(file); //기존에 있는 파일들은 삭제 
			    	 }
			    	 
			    	 if(os.equals("Windows 10")) {
			    		 newlocation = newlocation + "\\" + filesystemName; 
			    	 }
			    	 else if(os.equals("Linux")) {
			    		 newlocation = newlocation + "/" + filesystemName;  
			    	 }
			    	 else if(os.equals("Mac")) {
			    		 //맥북사면 수정예정 
			    	 }
			    	 
			    	 location = location + filesystemName;  
			    	 
					 Path oldfile = Paths.get(location); //임시로 파일을 업로드한 경로 
					 Path newfile = Paths.get(newlocation); ///옮겨야 할 경로 (번호 경로) 

					 //번호 경로로 옮기기 
					 Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING); 
					 
			   }
			   
			   BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw); 
			   BoardDTO boarddto = new BoardDTO(num, title, content, null, filesystemName, null, modifydate);
			   
			   int result = boarddao.updateBoard(boarddto);
			   
			   if(result != 0) {
				   viewName = "totalboardlist.do";  
			   }
			   else {
				   g.jsmessage("Unknown Error Message"); 
			   }
			}
			else {
				g.jsmessage("관리자 계정으로만 자료실 정보를 수정할 수 있습니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getLocalizedMessage()); 
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("자료실 정보를 수정하는데 오류가 발생하였습니다.");
		}
	}

}
