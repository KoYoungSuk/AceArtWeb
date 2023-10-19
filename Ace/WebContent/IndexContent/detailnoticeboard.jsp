<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold;">공지사항</H2>
  <hr>
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <c:choose> 
   <c:when test="${sessionScope.id eq 'admin'}"> 
   <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='modifynoticeboard.do?num=${sessionScope.detailnoticelist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
   <button type="button" class="btn btn-danger btn-sm" onclick="location.href='deletenoticeboard.do?num=${sessionScope.detailnoticelist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
  </c:when> 
  <c:otherwise></c:otherwise> 
   </c:choose>
  </div> 
 <table class="table">
 <tbody>
  <tr>
   <td>글 번호</td>
   <td>${sessionScope.detailnoticelist["num"]}</td> 
  </tr>
  <tr>
  <td>제목</td>
  <td>${sessionScope.detailnoticelist["title"]}</td> 
  </tr>
  <tr>
  <td>내용</td>
  <td>${sessionScope.detailnoticelist["content"]}</td> 
  </tr>
 <tr>
  <td>작성 날짜</td>
  <td>${sessionScope.detailnoticelist["savedate"]}</td>
 </tr> 
 <tr>
  <td>수정 날짜</td>
  <td>${sessionScope.detailnoticelist["modifydate"]}</td> 
 </tr> 
 </tbody>
</table>
</div>
</div>