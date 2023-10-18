<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 90px; margin: 10px;">
  <H2 style="font-weight: bold;">작가 소개  </H2> 
  <hr>
  <div style="text-align: right; ">
     <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
     <button class="btn btn-secondary btn-sm" onclick="location.href=''"><span class="material-symbols-outlined">create</span>추가(관리자용)</button>'
     </c:when> 
     <c:otherwise></c:otherwise> 
     </c:choose>
     <button class="btn btn-secondary btn-sm" onclick="location.href='artistlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
  </div> 
  <hr>
  
</div>
</div>