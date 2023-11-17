package com.wp.auntweb;

import java.io.IOException;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class PDFUploadServlet
 */
@WebServlet("/pdfupload.do")
public class PDFUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDFUploadServlet() {
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
		HttpSession session = request.getSession();
		Global g = new Global(response);
		
		String viewName = null; 
		String id = (String)session.getAttribute("id"); 
		
		try {
			if(id.equals("admin")) {
				String os = System.getProperty("os.name");
				String location = null;
				int maxSize = 1024 * 1024 * 1024 * 5; 
				
				if(os.equals("Windows 10")) {
					location = "C:\\Temp\\pdf\\"; 
				}
				else if(os.equals("Linux")) {
					location = "/mnt/hdd3/TextFiles/pdf/"; 
				}
				else if(os.equals("Mac")) {
					g.jsmessage("MacBook");
				}
				
				MultipartRequest multi = new MultipartRequest(
						request, 
						location, 
					    maxSize, 
						"UTF-8");
				
			   Enumeration<?> files = multi.getFileNames();
			    
			   String element = "";
			   String filesystemName = "";
			   
			   if(files.hasMoreElements()) {
				   element = (String)files.nextElement();
				   filesystemName = multi.getFilesystemName(element);
			   }
			   
			   g.jsmessage("filesystemName: " + filesystemName);
			   viewName = "pdf.jsp?pdfname=" + filesystemName; 
			}
			else {
				session.invalidate(); 
				g.jsmessage("Administrator Only");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		response.sendRedirect(viewName);
	}

}
