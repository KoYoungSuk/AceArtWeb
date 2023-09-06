package com.wp.auntweb;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.wp.auntweb.DAO.MemberDAO;
import com.wp.auntweb.DTO.MemberDTO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		String viewName = null;
		
		//Parameter from HTML
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String password_confirmed = request.getParameter("cpassword");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday"); 
		String password_hass = BCrypt.hashpw(password, BCrypt.gensalt(12)); //��й�ȣ ��ȣȭ(BCrypt �ؽ��Լ�) 
		Timestamp joindate = new Timestamp(System.currentTimeMillis()); //���� ��¥
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    try
  	    {
  	    	//��й�ȣ�� ��й�ȣ Ȯ���� ������ �˻�
  	    	if(password.equals(password_confirmed))
  	    	{
  	    		MemberDTO memberdto = new MemberDTO(id, password_hass, name, birthday, joindate); 
  	    		MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw); 
  	    		
  	    		int result = memberdao.insertMember(memberdto);
  	    		
  	    		if(result != 0)
  	    		{
  	    			viewName = "index.jsp?page=1"; 
  	    		}
  	    	}
  	    	else
  	    	{
  	    		g.jsmessage("��й�ȣ�� ��й�ȣ Ȯ���� �ùٸ��� ����.");
  	    	}
  	    }
  	    catch(Exception ex)
  	    {
  	    	g.jsmessage(ex.getMessage()); 
  	    }
  	    
  	    if(viewName != null)
  	    {
  	    	response.sendRedirect(viewName);
  	    }
  	    else
  	    {
  	    	g.jsmessage("ȸ������ ����");
  	    }
	}

}
