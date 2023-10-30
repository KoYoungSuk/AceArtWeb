<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
   <div class="col-lg-8" style="padding: 10px; margin: 90px;">
     <H2 style="font-weight: bold;">작가 정보 자세히 보기</H2>
     <hr>
     <div style="text-align: right;">
        <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
        <c:choose>
          <c:when test="${sessionScope.id eq 'admin'}">
            <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='modifyartist.do?num=${sessionScope.artistlist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
            <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='deleteartist.do?num=${sessionScope.artistlist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
          </c:when>
          <c:otherwise></c:otherwise> 
        </c:choose> 
     </div>  
     <table class="table">
       <tr>
         <td>작가 이름</td>
         <td>${sessionScope.artistlist['name']}</td> 
       </tr>
       <tr>
         <td>작가 학력&경력</td>
         <td>${sessionScope.artistlist['career']}</td> 
       </tr>
       <tr>
        <td>작품</td>
        <td>
         <img src="/Artist/${sessionScope.artistlist['num']}/${sessionScope.artistlist['work1']}" width="400" height="350" />&nbsp;&nbsp;
         <img src="/Artist/${sessionScope.artistlist['num']}/${sessionScope.artistlist['work2']}" width="400" height="350" /> 
        </td> 
       </tr>
       <tr>
        <td>추가 날짜</td> 
        <td>${sessionScope.artistlist['savedate']}</td> 
       </tr>
       <tr>
        <td>수정 날짜</td> 
        <td>${sessionScope.artistlist['modifydate']}</td> 
       </tr> 
     </table>
   </div> 
</div>