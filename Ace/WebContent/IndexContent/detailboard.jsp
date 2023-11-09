<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <h2 style="font-weight: bold;">자료실</h2>
 <hr>
 <div style="text-align: right;">
     <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose>
        <c:when test="${sessionScope.id eq 'admin'}">
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='modifyboardlist.do?num=${sessionScope.detailboardlist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
          <button type="button" class="btn btn-danger btn-sm" onclick="location.href='deleteboardlist.do?num=${sessionScope.detailboardlist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
        </c:when>
        <c:otherwise></c:otherwise> 
     </c:choose> 
 </div>
 <table class="table">
   <tr>
     <td><label for="title">제목 </label></td>
     <td>${sessionScope.detailboardlist['title']}</td> 
   </tr>
   <tr>
    <td><label for="content">내용</label></td>
    <td>${sessionScope.detailboardlist['content']}</td> 
   </tr>
   <tr>
    <td><label for="file">파일 다운로드 </label></td>
    <td><a href="downloadboard.do?num=${sessionScope.detailboardlist['num']}&filename=${sessionScope.detailboardlist['files']}">파일 다운로드(파일명: ${sessionScope.detailboardlist['files']})</a></td> 
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
</div>
</div>