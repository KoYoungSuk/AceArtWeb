<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <c:choose> 
 <c:when test="${sessionScope.id eq 'admin'}">
 <h2 style="font-weight: bold;">자료실 삭제</h2>
 <hr>
 <form action="deleteboardlist.do" method="POST"> 
 <div style="text-align: right;">
     <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <button type="submit" class="btn btn-danger btn-sm" ><span class="material-symbols-outlined">delete</span>삭제</button>
 </div>
 <table class="table">
   <tr>
     <td><label for="title">제목 </label></td>
     <td><input type="hidden" name="num" value="${sessionScope.detailboardlist['num']}" />${sessionScope.detailboardlist['title']}</td> 
   </tr>
   <tr>
    <td><label for="content">내용</label></td>
    <td>${sessionScope.detailboardlist['content']}</td> 
   </tr>
   <tr>
    <td><label for="access">접근 권한</label></td>
    <td>${sessionScope.detailboardlist['access']}</td> 
   </tr>
   <tr>
    <td><label for="file">파일 다운로드 </label></td>
    <td><input type="hidden" name="filename" value="${sessionScope.detailboardlist['files']}" />파일명: ${sessionScope.detailboardlist['files']}</td> 
   </tr> 
   <tr>
    <td><label for="savedate">작성 날짜 </label></td>
    <td>${sessionScope.detailboardlist['savedate']}</td> 
   </tr>
   <tr>
    <td><label for="modifydate">수정 날짜 </label></td>
    <td>${sessionScope.detailboardlist['modifydate']}</td> 
   </tr> 
 </table>
 </form>
 </c:when>
 <c:otherwise>
  <script>
   alert("관리자 계정으로만 자료실 정보 삭제가 가능합니다.");
   history.go(-1); 
  </script>
 </c:otherwise> 
 </c:choose>
</div>
</div>