<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px; ">
<H2 style="font-weight: bold; "> 작품 </H2> 
<hr> 
<div style="text-align: right;">
  <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
  <c:choose>
    <c:when test="${sessionScope.id eq 'admin' }">
       <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=26'"><span class="material-symbols-outlined">create</span>작성</button>
    </c:when>
    <c:otherwise></c:otherwise> 
  </c:choose> 
  <button class="btn btn-secondary btn-sm" onclick="location.href='workslist.do?desc=0'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
</div>
<table class="table">
 <thead>
   <tr>
    <th>번호</th>
    <th>작품명</th>
    <th>설치날짜</th>
    <th>작성날짜</th>
    <th>수정날짜</th> 
    <th>삭제</th> 
   </tr>
 </thead>
 <tbody>
  <c:forEach var="worksDTO" items="${sessionScope.workslist}" varStatus="status"> 
  <tr>  
   <td><c:out value="${worksDTO.num}" /></td>
   <td><a href="detailworks.do?num=${worksDTO.num}">${worksDTO.name}</a></td>
   <td><c:out value="${worksDTO.installdate}" /></td>
   <td><c:out value="${worksDTO.savedate}" /></td>
   <td><c:out value="${worksDTO.modifydate}" /></td> 
   <c:choose>
    <c:when test="${sessionScope.id eq 'admin'}">
      <td><a href="deleteworks.do?num='${worksDTO.num}'">삭제</a></td> 
    </c:when>
    <c:otherwise></c:otherwise> 
   </c:choose> 
  </tr> 
  </c:forEach>
 </tbody>
</table>
</div>
</div>
