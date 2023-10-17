package com.wp.auntweb;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.auntweb.DAO.MemberDAO;

/**
 * Servlet implementation class FindIDServlet
 */
@WebServlet("/findid.do")
public class FindIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIDServlet() {
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
		Global g = new Global(response); 
		
		//Parameters from HTML 
		String email = request.getParameter("email");
		
		//�����ͺ��̽� ���� ����(web.xml)���� ��������
		ServletContext application = request.getSession().getServletContext();
		String JDBC_Driver = application.getInitParameter("jdbc_driver");
		String db_url = application.getInitParameter("db_url");
		String db_id = application.getInitParameter("db_userid");
		String db_pw = application.getInitParameter("db_password");
		  	    
		try {
			MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
			
			String id = memberdao.findid(email);
			
			String content = "���̵� : " + id; 
			String title = "���̽������������� ���̵� �˷��帳�ϴ�. (�̸��� �ּ�: " + email + " )"; 
			
		    SendMailClass sm = new SendMailClass(request);
		    
		    sm.sendMail(email, content, title);
		    
		    g.jsmessage("�Է��� �̸��� �ּҷ� ���̵� ������Ƚ��ϴ�. "); 
		    
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage()); 
		}
	}

}
