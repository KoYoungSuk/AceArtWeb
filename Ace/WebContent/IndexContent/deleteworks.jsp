<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
  <div class="col-lg-8" style="padding: 10px; margin: 90px;">
   <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
       <H2 style="font-weight: bold;">작품 삭제</H2>
       <hr>
       <form action="deleteworks.do" method="POST">
       <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" onclick="history.go(-1);" type="button"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">delete</span>삭제</button>&nbsp;&nbsp; 
       </div>       
       <table class="table">
         <tr>
           <td>작품명</td>
           <td>
           ${sessionScope.detailworkslist['name']}
           <input type="hidden" name="num" value="${sessionScope.detailworkslist['num']}" /> 
           </td> 
         </tr>
         <tr>
          <td>작품사진</td>
          <td><img src="/Pictures/${sessionScope.detailworkslist['num']}/${sessionScope.detailworkslist['picture']}" width="500" height="350" /></td> 
         </tr>
         <tr>
          <td>작품 파일명</td>
          <td>${sessionScope.detailworkslist['picture']}</td> 
         </tr> 
         <tr>
          <td>작품설명</td>
          <td>${sessionScope.detailworkslist['description']}</td> 
         </tr>
         <tr>
          <td>설치날짜</td>
          <td>${sessionScope.detailworkslist['installdate']}</td> 
         </tr>
         <tr>
          <td>작성날짜</td>
          <td>${sessionScope.detailworkslist['savedate']}</td> 
         </tr> 
         <tr>
          <td>수정날짜</td>
          <td>${sessionScope.detailworkslist['modifydate']}</td> 
         </tr> 
       </table>
       </form>
     </c:when>
     <c:otherwise>
      <p>관리자만 작품 정보 삭제가 가능합니다.</p> 
     </c:otherwise> 
   </c:choose> 
  </div>
</div>