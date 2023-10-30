<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <c:choose>
  <c:when test="${sessionScope.id eq 'admin'}"> <!--  관리자 계정(admin)으로 로그인했을때  -->
   <H2 style="font-weight: bold; ">관리자 모드 </H2>
   <hr>
   <div style="text-align: center; ">
     <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='../manager/status'"><span class="material-symbols-outlined">dns</span>Server Status</button> 
     <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='totalmemberlist.do'"><span class="material-symbols-outlined">person</span>Member Management</button>
     <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='pdfupload.jsp'"><span class="material-symbols-outlined">picture_as_pdf</span>PDF Upload</button> 
   </div> 
  </c:when>
  <c:otherwise>
   <p>관리자 계정으로만 사용이 가능합니다. </p> 
  </c:otherwise> 
 </c:choose> 
</div>
</div>


