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
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    
		try {
			if(id.equals("admin")) {
				//�ϴ� ���Ϻ��� ���� �������� 
				String location = null;
				String os = System.getProperty("os.name");
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\Artist\\" + num; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/Artist/" + num; 
				}
				else if(os.equals("Mac")) {
					//https://www.apple.com/kr/shop/bag //360����(ȯ�� �������ϰ� �޷��� ����ϸ� 3600�޷���...) 
				}
				
				File file = new File(location);
				
				if(file.exists()) { //������ �����ϸ� ���� �� ���� �� ���� ���� 
  	    			FileUtils.cleanDirectory(file); //���� �� ���� ���� 
  	                if(file.isDirectory()) {
  	                	file.delete();  //���� ���� 
  	                }
  	    		}
				
				ArtistDAO artistdao = new ArtistDAO(JDBC_Driver, db_url, db_id, db_pw);
				
				int result = artistdao.deleteArtist(num);
				
				if(result != 0) {
					viewName = "totalartistlist.do?desc=0"; 
				}
				else {
					g.jsmessage("Unknown Error Message");
				}
			}
			else {
				g.jsmessage("�����ڸ� �۰� ������ ������ �� �ֽ��ϴ�.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("�۰� ������ �����ϴµ� ������ �߻��Ͽ����ϴ�.");
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
