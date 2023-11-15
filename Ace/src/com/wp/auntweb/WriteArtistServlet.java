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
import com.wp.auntweb.DAO.ArtistDAO;
import com.wp.auntweb.DTO.ArtistDTO;

/**
 * Servlet implementation class WriteArtistServlet
 */
@WebServlet("/writeartist.do")
public class WriteArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteArtistServlet() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String viewName = null;
		Global g = new Global(response);
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		Timestamp savedate = new Timestamp(System.currentTimeMillis()); //현재 날짜
		
		ServletContext application = request.getSession().getServletContext();
		//START - 데이터베이스 연결 준비 (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - 데이터베이스 연결 준비 (web.xml)
  	    
		try {
			if(id.equals("admin")) {
                ArtistDAO artistdao = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int maxnum = artistdao.getMaxnum();
				
				maxnum = maxnum + 1;
				
				int maxSize = 1024 * 1024 * 1024 * 5;
				
				String location = null; 
				String os = System.getProperty("os.name");
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\Artist\\" + maxnum; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Artist/" + maxnum; 
				}
				else if(os.equals("Mac")) {
					
				}
				
	            Path directoryPath = Paths.get(location); //디렉터리 경로 설정 
				
				if(!Files.exists(directoryPath)) 
				{
					Files.createDirectory(directoryPath); //디렉터리 생성 
				}
				
				if(os.equals("Windows 10")) {
					location = location + "\\"; 
				}
				else if(os.equals("Linux")) {
					location = location + "/"; 
				}
				else if(os.equals("Mac")) {
					
				}
				
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
			   
			   String name = multi.getParameter("name");
			   String career = multi.getParameter("career");
			   name = XssPreventer.escape(name); 
               career = XssPreventer.escape(career); 
               
				ArtistDTO artistDTO = new ArtistDTO(maxnum, name, career, filesystemName_list[1], filesystemName_list[0], savedate, null);
				
				int result = artistdao.insertArtist(artistDTO);
				
				if(result != 0) {
					viewName = "totalartistlist.do";
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
				
			}
			else {
				g.jsmessage("관리자만 작가정보 추가가 가능합니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		

		if(viewName != null) {
			response.sendRedirect(viewName); 
		}
		else {
			g.jsmessage("작가 정보를 추가하는데 오류가 발생하였습니다."); 
		}
	}

}
