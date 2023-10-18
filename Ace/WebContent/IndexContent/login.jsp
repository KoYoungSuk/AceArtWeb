<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<c:choose>
<c:when test="${sessionScope.id eq null}">
<div class="col-lg-4" style="padding: 10px; margin: 90px; ">
   <H2 style="font-weight: bold; ">로그인 </H2>
   <hr> 
   <form action="login.do" method="POST">
     <table class="table">
      <tr>
	    <td>&nbsp;&nbsp;<label for="ID">ID:</label></td>
	    <td><input class="form-control" type="text" name="id" required /></td>
	  </tr>
	  <tr>
	    <td>&nbsp;&nbsp;<label for="Password">Password:</label></td>
	    <td><input class="form-control" type="password" name="password" required /></td>
	  </tr>
     </table>
      <div style="text-align: right">
	  <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);" ><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>&nbsp;&nbsp; 
	  <button type="submit" class="btn btn-secondary btn-sm" ><span class="material-symbols-outlined">login</span>로그인</button>&nbsp;&nbsp; 
	  <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='./index.jsp?page=3'"><span class="material-symbols-outlined">person_add</span>회원가입</button>
	  <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='./index.jsp?page=11'"><span class="material-symbols-outlined">search</span>아이디/비밀번호 찾기</button>
	 </div>
   </form>
</div>
</c:when>
<c:otherwise>
  <p>이미 로그인되어 있습니다.</p> 
</c:otherwise> 
</c:choose>
</div>