package com.wp.auntweb;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SMTPAuthenticator extends Authenticator{  //부모 클래스: Authenticator //자식 클래스: SMTPAuthenticator 
   HttpServletRequest request = null;
   
   public SMTPAuthenticator(HttpServletRequest request) {
	   this.request = request; 
   }
   
   @Override 
   protected PasswordAuthentication getPasswordAuthentication() {
   	
   	//web.xml에서 메일 계정 아이디 및 비밀번호 불러오기 
   	ServletContext application = request.getSession().getServletContext();
   	
   	String id = application.getInitParameter("email_id");
   	String password = application.getInitParameter("email_pw"); 
   	
   	//System.out.println("password: " + password); 
       return new PasswordAuthentication(id, password);
   }
}
