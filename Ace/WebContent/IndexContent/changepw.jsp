<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
<c:choose>
 <c:when test="${sessionScope.id ne null}"> <!-- 로그인되어 있을때 --> 
   <H2 style="font-weight: bold; ">비밀번호 변경</H2>
   <hr>
   <form action="changepw.do" method="POST">
   <table class="table">
    <tr>
     <td><label for="currentpassword">현재 비밀번호: </label></td>
     <td><input type="password" class="form-control" name="currentpassword" /></td>
    </tr>
    <tr>
     <td><label for="newpassword">새 비밀번호: </label></td>
     <td><input type="password" class="form-control" name="newpassword" /></td> 
    </tr>
    <tr>
     <td><label for="newpasswordc">새 비밀번호 확인: </label></td>
     <td><input type="password" class="form-control" name="newpasswordc" /></td> 
    </tr> 
   </table>
   <div style="text-align: right;">
    <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
    &nbsp;&nbsp; 
    <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">key</span>비밀번호 변경</button> 
   </div>
   </form>
 </c:when>
 <c:otherwise> <!--  로그인되어 있지 않을때  -->
  <p>로그인한 사용자만 비밀번호 변경이 가능합니다.</p> 
 </c:otherwise> 
</c:choose> 
</div>
</div>