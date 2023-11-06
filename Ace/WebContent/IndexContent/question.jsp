<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px;">
   <h2 style="font-weight: bold;">질문과 답변 게시판</h2>
   <hr>
   <div style="text-align: right; ">
      <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href=''"><span class="material-symbols-outlined">create</span>질문하기</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href=''"><span class="material-symbols-outlined">refresh</span>새로고침</button>
   </div>
   <table class="table">
     <thead>
     <tr>
      <th>번호</th>
      <th>제목</th>
      <th>접근 모드</th>
      <th>작성자</th>
      <th>작성날짜</th>
      <th>수정날짜</th> 
     </tr>
     </thead> 
     <tbody>
     <c:forEach var="questionDTO" items="${sessionScope.totalquestionlist}" varStatus="status">
      <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td> 
      </tr>
     </c:forEach>
     </tbody> 
   </table>
 </div> 
</div>