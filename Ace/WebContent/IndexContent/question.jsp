<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px;">
   <h2 style="font-weight: bold;">질문과 답변 게시판</h2>
   <hr>
   <div style="text-align: right; ">
   
   </div>
   <table class="table">
     <thead>
      <th>번호</th>
      <th>제목</th>
      <th>접근 모드</th>
      <th>작성자</th>
      <th>작성날짜</th>
      <th>수정날짜</th> 
     </thead> 
     <tbody>
     <c:forEach var="questionDTO" items="${sessionScope.totalquestionlist}" varStatus="status">
      
     
     </c:forEach>
     </tbody> 
   </table>
 </div> 
</div>