package com.wp.auntweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Global {
   
	HttpServletResponse response;
	public Global(HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");  //UTF-8 
		this.response = response;
	}
	public void jsmessage(String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(message != null) {
			out.println("alert('" + message.replace('\n', ' ') + "');");
		}
		else {
			out.println("alert('Null Error');"); 
		}
		out.println("history.go(-1);");
		out.println("</script>");
	}
	public void errorcode(int code) throws IOException {
		response.sendError(code);
	}
}
