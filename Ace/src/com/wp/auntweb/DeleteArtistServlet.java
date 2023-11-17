package com.wp.auntweb;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.wp.auntweb.DAO.ArtistDAO;

/**
 * Servlet implementation class DeleteArtistServlet
 */
@WebServlet("/deleteartist.do")
public class DeleteArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArtistServlet() {
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
				//일단 파일부터 먼저 삭제하자 
				String location = null;
				String os = System.getProperty("os.name");
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\Artist\\" + num; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Artist/" + num; 
				}
				else if(os.equals("Mac")) {
					//https://www.apple.com/kr/shop/bag //360만원(환율 생각안하고 달러로 계산하면 3600달러임...) 
				}
				
				File file = new File(location);
				
				if(file.exists()) { //파일이 존재하면 폴더 내 파일 및 폴더 삭제 
  	    			FileUtils.cleanDirectory(file); //폴더 내 파일 삭제 
  	                if(file.isDirectory()) {
  	                	file.delete();  //폴더 삭제 
  	                }
  	    		}
				
				ArtistDAO artistdao = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int result = artistdao.deleteArtist(num);
				
				if(result != 0) {
					viewName = "totalartistlist.do"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("관리자만 작가 정보를 삭제할 수 있습니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("작가 정보를 삭제하는데 오류가 발생하였습니다.");
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
