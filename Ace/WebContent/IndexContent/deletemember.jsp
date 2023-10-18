<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
  <c:choose>
   <c:when test="${sessionScope.id ne null}">
    <H2 style="font-weight: bold;">회원정보 삭제 </H2>
    <hr>
    <H5>경고: 회원정보를 삭제하면 다시 복구할 수 없습니다. 삭제 버튼을 누르면 삭제됩니다. </H5>
    <form action="deletemember.do" method="POST"> 
    <table class="table">
     <tbody>
     <tr>
      <td><label for="id">아이디: </label></td>
      <td>${sessionScope.id}</td>
     </tr>
     <tr>
      <td><label for="name">이름: </label></td>
      <td>${sessionScope.name}</td> 
     </tr>
     <tr>
      <td><label for="birthday">생년월일: </label></td>
      <td>${sessionScope.birthday}</td>
     </tr>
     <tr>
      <td><label for="email">이메일: </label></td>
      <td>${sessionScope.email}</td>
     </tr>
     <tr>
      <td><label for="joindate">가입 날짜: </label></td>
      <td>${sessionScope.joindate}</td> 
     </tr>
     </tbody>
    </table>
    <div style="text-align: right;">
      <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1)"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기 </button>&nbsp;&nbsp; 
      <button class="btn btn-danger btn-sm" type="submit"><span class="material-symbols-outlined">delete</span>삭제 </button>
    </div>
    </form> 
   </c:when>
   <c:otherwise>
    <p>회원정보를 삭제하려면 먼저 로그인을 해야 합니다.</p> 
   </c:otherwise> 
  </c:choose> 
</div> 
</div>