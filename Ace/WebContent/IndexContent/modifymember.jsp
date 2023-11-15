<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <c:choose> 
  <c:when test="${sessionScope.id ne null}"> 
  <H2 style="font-weight: bold; ">회원정보 수정</H2>
  <hr>
  <form action="modifymember.do" method="POST"> 
  <table class="table">
  <tbody>
   <tr>
    <td><label for="id" style="font-weight: bold; font-size: 20px;">ID:</label></td>
    <td>${sessionScope.id}</td>
   </tr>
   <tr>
    <td><label for="name" style="font-weight: bold;  font-size: 20px;">이름:</label></td>
    <td><input type="text" class="form-control" name="name" value="${sessionScope.name}" /></td>
   </tr>
   <tr>
    <td><label for="birthday" style="font-weight: bold; font-size: 20px;">생년월일:</label></td>
    <td><input type="text" class="form-control" name="birthday" value="${sessionScope.birthday}" /></td>
   </tr>
   <tr>
    <td><label for="email" style="font-weight: bold; font-size: 20px;">이메일 주소 :</label></td>
    <td><input type="text" class="form-control" name="email" value="${sessionScope.email}" required /></td>
   </tr>
  </tbody>
  </table>
  <div style="text-align: right; "> 
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1); "><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <button type="submit" class="btn btn-secondary btn-sm"><span class="material-symbols-outlined">create</span>회원정보 수정 </button>
  </div>
  </form> 
  </c:when>
  <c:otherwise>
  <script>
    alert("로그인이 필요합니다.");
    history.go(-1); 
  </script>
  </c:otherwise> 
</c:choose>
</div>
</div>