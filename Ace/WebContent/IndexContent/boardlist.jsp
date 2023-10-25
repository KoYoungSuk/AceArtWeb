<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px; ">
 <H2 style="font-weight: bold; ">자료실</H2>
 <hr> 
 <div style="text-align: right; ">
    <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
    <c:choose>
      <c:when test="${sessionScope.id eq 'admin'}">
          <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=22'"><span class="material-symbols-outlined">create</span>작성</button>
      </c:when>
      <c:otherwise></c:otherwise> 
    </c:choose> 
    <button class="btn btn-secondary btn-sm" onclick="location.href='totalboardlist.do?desc=0'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
 </div> 
 <table class="table">
   <thead>
     <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성 날짜</th>
      <th>수정 날짜</th> 
      <th>삭제</th> 
     </tr>
   </thead>
   <tbody>
     <c:forEach var="BoardDTO" items="${sessionScope.totalboardlist}" varStatus="status">
      <tr>
       <td><c:out value="${BoardDTO.num}" /></td>
       <td><a href="detailboardlist.do?num=${BoardDTO.num}">${BoardDTO.title}</a></td>
       <td><c:out value="${BoardDTO.savedate}" /></td>
       <td><c:out value="${BoardDTO.modifydate}" /></td>
       <td>
       <c:choose> 
       <c:when test="${sessionScope.id eq 'admin'}">
       <a href="deleteboardlist.do?num=${BoardDTO.num}">삭제</a></td> 
       </c:when> 
       <c:otherwise>
       </c:otherwise>
       </c:choose> 
      </tr>
     </c:forEach>
   </tbody>
 </table>
</div>
</div>