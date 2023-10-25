package com.wp.auntweb;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class SendMailClass {
   HttpServletRequest request = null;
   
   public SendMailClass(HttpServletRequest request) {
	   this.request = request; 
   }
   
   //�Ű�����: ���� �ּ�, ����, ����
   //����� ���� ������ ����(vheh1936@gmail.com)�� ����ϵ��� �Ǿ����� 
   public void sendMail(String mailaddress, String content, String title) throws MessagingException{
	    Properties p = new  Properties();
		p.put("mail.smtp.host", "smtp.gmail.com"); 
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth",  "true");
		p.put("mail.smtp.debug",  "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.socketFactory.port", "587");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//p.put("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2"); //Change TLS Protocol Version to 1.2 
       //I typed this code because JavaMail 1.4.7 doesn't support TLS1.2, But OpenJDK 11 only supports TLS1.2 or higher. 
		//JDK 8 support old TLS Version. But using old TLS version is dangerous for security reason.  
		
		Authenticator auth = new SMTPAuthenticator(request); //�ڽ� Ŭ������ �����ڸ� �ҷ��´�. 
		
		Session session = Session.getInstance(p, auth); 
		
		//Session session = Session.getDefaultInstance(p, null); 
		
		Message message = new MimeMessage(session);
		
		message.setSubject(title); //���� 
		
		Address fromAddr = new InternetAddress("vheh1936@gmail.com");
		
		message.setFrom(fromAddr); //������ ��� 
		
		Address toAddr = new InternetAddress(mailaddress);
		
		message.addRecipient(Message.RecipientType.TO, toAddr); //�޴� ���
		
		message.setContent(content, "text/html;charset=UTF-8");
		
		Transport.send(message);
   }
}
