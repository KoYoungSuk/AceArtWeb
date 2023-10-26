package com.wp.auntweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadBoardServlet
 */
@WebServlet("/downloadboard.do")
public class DownloadBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadBoardServlet() {
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
				
		try {
			int num = Integer.parseInt(request.getParameter("num")); 
			String filename = request.getParameter("filename"); 
			String os = System.getProperty("os.name"); 
			
			String location = null; 
			if(os.equals("Windows 10")) {
				location = "C:\\Temp\\Board\\" + num + "\\" + filename; 
			}
			else if(os.equals("Linux")) {
				location = "/mnt/hdd3/TextFiles/Board/" + num + "/" + filename; 
			}
			else if(os.equals("Mac")) {
				//맥북 사면 코드 수정예정 
			}
					
			File file = new File(location); //서버 파일 경로 설정 
			FileInputStream in = new FileInputStream(location);
			
			filename = new String(filename.getBytes("utf-8"), "8859_1"); //한글파일 처리 
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			
			OutputStream output = response.getOutputStream();
			
			int length;
			byte[] b = new byte[(int)file.length()];
			
			while((length = in.read(b)) > 0) {
				output.write(b,0,length); //서버에서 바이트 배열로 받아온 파일 작성 
			}
			output.flush();
			
			output.close();
			in.close();

			
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
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
