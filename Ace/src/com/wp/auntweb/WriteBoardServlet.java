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
import com.wp.auntweb.DAO.BoardDAO;
import com.wp.auntweb.DTO.BoardDTO;

/**
 * Servlet implementation class WriteBoardServlet
 */
@WebServlet("/writeboard.do")
public class WriteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoardServlet() {
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
		HttpSession session = request.getSession(); 
		Global g = new Global(response);
		String viewName = null;
		
		int maxSize = 1024 * 1024 * 1024 * 5; //KiloByte * MegaByte * GigaByte (5GB)
		
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
  	    Timestamp savedate = new Timestamp(System.currentTimeMillis()); //현재 날짜
  	    
		try {
			if(id.equals("admin")) {
				
                //개발환경: Windows 10(Windows 11)
				//서버환경: Linux(Ubuntu 20.04 LTS)
				//맥북 사면 맥북에서도 개발할 예정.
				
			    String os = System.getProperty("os.name"); //OS Check 
			  	
			    
			    BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
				    
				int maxnum = boarddao.getMaxNumBoard();
				
				maxnum = maxnum + 1;
				
				String location = null; 
				
				if(os.equals("Linux"))
				{
					location = "/mnt/hdd3/TextFiles/Board/" + maxnum ; //Linux 
				}
				else if(os.equals("Windows 10"))
				{
					location = "C:\\Temp\\Board\\" + maxnum; //Windows 10&11 
				}
				else if(os.equals("Mac")) {
					//맥에서는 아직 개발을 안했음. 맥북 살려고 넣었음. 
				}
				
				Path directoryPath = Paths.get(location); //디렉터리 경로 설정 
				
				if(!Files.exists(directoryPath)) 
				{
					Files.createDirectory(directoryPath); //디렉터리 생성 
				}
			    
				if(os.equals("Linux"))
				{
					location = location + "/";  //Linux 
				}
				else if(os.equals("Win"))
				{
					location = location + "\\"; //Windows 
				}
				else if(os.equals("Mac")) {
					//맥에서는 아직 개발을 안했음. 맥북 살려고 넣었음. 
				}
				 
			  	 
				MultipartRequest multi = new MultipartRequest(request,
		 			      location,
						  maxSize,
						  "utf-8",
						  new DefaultFileRenamePolicy());
				
			    String title = multi.getParameter("title");
			    String content = multi.getParameter("content");
			    
			    title = XssPreventer.escape(title); 
			    content = XssPreventer.escape(content);
			    
			    Enumeration<?> files = multi.getFileNames();
			    
			    String element = "";
			    String filesystemName = "";
			    
			    if(files.hasMoreElements()) {
			    	element = (String)files.nextElement();
			    	filesystemName = multi.getFilesystemName(element);
			    }
			    
			    BoardDTO boarddto = new BoardDTO(maxnum, title, content, null, filesystemName, savedate, null); 
			    
			    int result = boarddao.insertBoard(boarddto);
			    
			    if(result != 0) {
			    	viewName = "totalboardlist.do?desc=0";
			    }
			    else {
			    	g.jsmessage("Unknown Error Message"); 
			    }
			}
			else {
				g.jsmessage("관리자 계정으로만 자료실 등록이 가능합니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("자료실 정보를 등록하는데 오류가 발생하였습니다.");
		}
	}

}
