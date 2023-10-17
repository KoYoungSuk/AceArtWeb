package com.wp.auntweb;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SMTPAuthenticator extends Authenticator{
   HttpServletRequest request = null;
   
   public SMTPAuthenticator(HttpServletRequest request) {
	   this.request = request; 
   }
   
   @Override 
   protected PasswordAuthentication getPasswordAuthentication() {
   	
   	//web.xml���� ���� ���� ���̵� �� ��й�ȣ �ҷ����� 
   	ServletContext application = request.getSession().getServletContext();
   	
   	String id = application.getInitParameter("email_id");
   	String password = application.getInitParameter("email_pw"); 
   	
   	//System.out.println("password: " + password); 
       return new PasswordAuthentication(id, password);
   }
}
