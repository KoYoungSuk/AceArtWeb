package com.wp.auntweb;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.wp.auntweb.DAO.MemberDAO;

/**
 * Servlet implementation class FindPWServlet
 */
@WebServlet("/findpw.do")
public class FindPWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPWServlet() {
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
		Global g = new Global(response); 
		
		//Parameters from HTML
		String id = request.getParameter("id");
		String email = request.getParameter("email"); 
		
		//데이터베이스 연결 정보(web.xml)에서 가져왔음
	    ServletContext application = request.getSession().getServletContext();
	    String JDBC_Driver = application.getInitParameter("jdbc_driver");
		String db_url = application.getInitParameter("db_url");
		String db_id = application.getInitParameter("db_userid");
		String db_pw = application.getInitParameter("db_password");
		
		try {
			
			String random_pw = RandomStringUtils.randomAlphanumeric(10); //10자리로 랜덤 비밀번호 생성
			
			String password_hash = BCrypt.hashpw(random_pw, BCrypt.gensalt(12)); //HASH는 단방향 암호화!
			
			MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			int result = memberdao.clearPassword(id, password_hash, email);
			
			if(result != 0) {
				 SendMailClass sm = new SendMailClass(request);
				 
				 String title = "에이스도시조형에서 임시 비밀번호를 보내드렸습니다. (이메일 주소: " + email + ", 아이디: " + id + ")"; 
				 String content = "임시 비밀번호: " + random_pw;
				 
				 sm.sendMail(email, content, title);
				 
				 g.jsmessage("입력한 이메일 주소로 임시 비밀번호를 보내드렸습니다. 로그인 후 반드시 비밀번호를 바꿔 주세요. "); 
			}
			else {
				g.jsmessage("Unknown Error Message"); 
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
				  	    
	}

}
