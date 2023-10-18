package com.wp.auntweb;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.wp.auntweb.DAO.MemberDAO;

/**
 * Servlet implementation class ChangePWServlet
 */
@WebServlet("/changepw.do")
public class ChangePWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePWServlet() {
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
		
		//Parameter from HTML
		String currentpassword = request.getParameter("currentpassword");
		String newpassword = request.getParameter("newpassword");
		String newpasswordc = request.getParameter("newpasswordc"); //New Password Confirmed
		
		//데이터베이스 연결 정보(web.xml)에서 가져왔음
	    ServletContext application = request.getSession().getServletContext();
	    String JDBC_Driver = application.getInitParameter("jdbc_driver");
		String db_url = application.getInitParameter("db_url");
		String db_id = application.getInitParameter("db_userid");
		String db_pw = application.getInitParameter("db_password");
		
		String id = (String)session.getAttribute("id");
		
		try {
			MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw); 
			
			Map<String, String> memberlist = memberdao.GetMemberListById(id);
			
			if(BCrypt.checkpw(currentpassword, memberlist.get("password"))) {
				if(newpassword.equals(newpasswordc)) {
					String newpassword_hash = BCrypt.hashpw(newpassword, BCrypt.gensalt(12)); 
					
					int result = memberdao.updatePassword(id, newpassword_hash);
					
					if(result != 0) {
						session.invalidate(); 
						viewName = "index.jsp?page=1"; 
					}
					else {
						g.jsmessage("Unknown Error Message");
					}
				}
				else {
					g.jsmessage("비밀번호와 비밀번호 확인이 다릅니다.");
				}
			}
			else {
				g.jsmessage("현재 비밀번호가 올바르지 않습니다.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
	}

}
