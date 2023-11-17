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
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try {
			if(id != null) {
				if(id.equals("admin")) {
					BoardDAO boarddao = new BoardDAO(JDBC_Driver, db_url, db_id, db_pw);
					Map<String, String> boardlist = boarddao.getBoardListByNum(num, false);
					
					if(boardlist != null) {
						String num_new = boardlist.get("num");
						
						if(num_new != null) {
							session.setAttribute("detailboardlist", boardlist);
							viewName = "index.jsp?page=25"; 
						}
					}
					else {
						g.jsmessage("Unknown Error Message");
					}
				}
				else {
					session.invalidate(); 
					g.jsmessage("������ �������θ� �ڷ�� ������ ������ �� �ֽ��ϴ�."); 
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
			g.jsmessage("�ڷ�� ������ �ҷ����µ� ������ �߻��Ͽ����ϴ�.");
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
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    String os = System.getProperty("os.name"); //OS Check 
        String location = null; 
        
  	    if(os.equals("Windows 10")) {
  	    	location = "C:\\Temp\\Board\\"; 
  	    }
  	    else if(os.equals("Linux")) {
  	    	location = "/mnt/hdd3/TextFiles/Board/"; 
  	    }
  	    else if(os.equals("Mac")) {
  	    	//�ƺϻ�� �ڵ� �������� 
  	    }
  	   
		try {
			if(id != null) {
				if(id.equals("admin")) {
					
					int maxSize = 1024 * 1024 * 1024 * 5;
					
					//���� ���� ���ε� 
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
					
					Timestamp modifydate = new Timestamp(System.currentTimeMillis()); //���� ��¥
					
					Enumeration<?> files = multi.getFileNames();
					    
					String element = "";
					String filesystemName = "";
					 
					if(files.hasMoreElements()) {
					      element = (String)files.nextElement();
					      filesystemName = multi.getFilesystemName(element); //������ ���ε��� ���ϸ� 
					}
					 
				    String newlocation = null;
				    
				    if(filesystemName != null) { //������ �������� ��� �� ������ ������ ���ε�Ǵµ�, �� ��θ� �ٲ���� �Ѵ�. 
				    	 //��ȣ�� ���缭 ��θ� �ٲ��ش�. 
				    	 
				    	 newlocation = location + num; 

				    	 File file = new File(newlocation);
				    	 
				    	 if(file.exists()) {
				    		 FileUtils.cleanDirectory(file); //������ �ִ� ���ϵ��� ���� 
				    	 }
				    	 
				    	 if(os.equals("Windows 10")) {
				    		 newlocation = newlocation + "\\" + filesystemName; 
				    	 }
				    	 else if(os.equals("Linux")) {
				    		 newlocation = newlocation + "/" + filesystemName;  
				    	 }
				    	 else if(os.equals("Mac")) {
				    		 //�ƺϻ�� �������� 
				    	 }
				    	 
				    	 location = location + filesystemName;  
				    	 
						 Path oldfile = Paths.get(location); //�ӽ÷� ������ ���ε��� ��� 
						 Path newfile = Paths.get(newlocation); ///�Űܾ� �� ��� (��ȣ ���) 

						 //��ȣ ��η� �ű�� 
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
					session.invalidate(); 
					g.jsmessage("������ �������θ� �ڷ�� ������ ������ �� �ֽ��ϴ�.");
				}
			}
			else {
				session.invalidate(); 
				g.jsmessage("Null Error Message");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getLocalizedMessage()); 
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("�ڷ�� ������ �����ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
	}

}
