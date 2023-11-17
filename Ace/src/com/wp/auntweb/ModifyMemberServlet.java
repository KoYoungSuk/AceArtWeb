package com.wp.auntweb;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.wp.auntweb.DAO.MemberDAO;
import com.wp.auntweb.DTO.MemberDTO;

/**
 * Servlet implementation class ModifyMemberServlet
 */
@WebServlet("/modifymember.do")
public class ModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberServlet() {
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
		HttpSession session = request.getSession();
		String viewName = null;
		Global g = new Global(response);
		
		String id = (String)session.getAttribute("id");
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
  	    try
  	    { 
  	    	MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
  	    	
  	    	Map<String, String> memberlist = memberdao.GetMemberListById(id);
  	    	
  	    	if(memberlist != null)
  	    	{
  	    		if(memberlist.get("id") != null) {
  	    			viewName = "index.jsp?page=10"; 
  	  	    		session.setAttribute("birthday", memberlist.get("birthday"));
  	  	    		session.setAttribute("joindate", memberlist.get("joindate"));
  	  	    		session.setAttribute("email", memberlist.get("email")); 
  	    		}
  	    	}
  	    	else
  	    	{
  	    		g.jsmessage("Null Error Message.");
  	    	}
  	    }
  	    catch(Exception ex)
  	    {
  	    	g.jsmessage(ex.getMessage());
  	    }
  	    
  	    if(viewName != null)
  	    {
  	    	RequestDispatcher view = request.getRequestDispatcher(viewName);
  		    view.forward(request, response);
  	    }
  	    else
  	    {
  	    	g.jsmessage("�˼����� �����Դϴ�."); 
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
		String viewName = null;
		HttpSession session = request.getSession(); 
		
		String id = (String)session.getAttribute("id"); 
		
		//Parameters from HTML
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email"); 
		name = XssPreventer.escape(name);
		birthday = XssPreventer.escape(birthday);
		email = XssPreventer.escape(email);
		
		ServletContext application = request.getSession().getServletContext();
		//START - �����ͺ��̽� ���� �غ� (web.xml) 
    	String JDBC_Driver = application.getInitParameter("jdbc_driver");
  	    String db_url = application.getInitParameter("db_url");
  	    String db_id = application.getInitParameter("db_userid");
  	    String db_pw = application.getInitParameter("db_password");
  	    //END - �����ͺ��̽� ���� �غ� (web.xml)
  	    
		try
		{
			String email_id = ""; 
			MemberDAO memberdao = new MemberDAO(JDBC_Driver, db_url, db_id, db_pw);
			Map<String, String> memberlist = memberdao.GetMemberListById(id); 
		    if(memberlist != null) {
		    	email_id = memberlist.get("email"); //���� �α����� ID�� �̸��� ���� 
		    	if(email_id == null) {
		    		email_id = ""; //Null�̸� �׳� �� ������ ����. 
		    	}
		    }
		    
			if(email != null) {
				int countnum = 0; 
				if(email_id.equals(email)) { //���� �α����� ID�� �̸��ϰ� �Է��� �̸����� ����. -> �̸����� ��ġ�� �ʴٴ� ������ ����. 
					countnum = 0; 
				}
				else { //�װ� �ƴϸ� �̸��� �ּҴ� �ߺ��Ǽ��� �ȵ�. 
					countnum = memberdao.checkemail(email); 
				}
				
				if(countnum == 0) {
					MemberDTO memberdto = new MemberDTO(id, null, name, birthday, null, email); 
					
					int result = memberdao.updateMember(memberdto);
					
					if(result != 0) {
						viewName = "index.jsp?page=1";
					}
					else {
						g.jsmessage("Unknown Error Message."); 
					}
				}
				else {
					g.jsmessage("�̸����� �ߺ��Ǽ��� �ȵ˴ϴ�.");
				}
			}
			else {
				g.jsmessage("�̸����� �ʼ��Դϴ�.");
			}
		}
		catch(Exception ex) {
			g.jsmessage(ex.getMessage());
		}
		
		if(viewName != null) {
			response.sendRedirect(viewName);
		}
		else {
			g.jsmessage("ȸ�� ���� ���� ���� ������ �߻��Ͽ����ϴ�."); 
		}
	}

}
