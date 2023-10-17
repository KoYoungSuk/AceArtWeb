<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!--  JSP 모듈 파일 임포트 -->
<c:import url="IndexContent/default.jsp" var="defaultcontent"></c:import> 
<c:import url="IndexContent/login.jsp" var="logincontent"></c:import> 
<c:import url="IndexContent/signup.jsp" var="signupcontent"></c:import> 
<c:import url="IndexContent/introduction.jsp" var="introcontent"></c:import> 
<c:import url="IndexContent/works.jsp" var="workscontent"></c:import>
<c:import url="IndexContent/selectedmember.jsp" var="selectedmembercontent"></c:import>
<c:import url="IndexContent/findidandpw.jsp" var="findidandpwcontent"></c:import>
<c:import url="IndexContent/modifymember.jsp" var="modifymembercontent"></c:import> 
<c:import url="IndexContent/error403.jsp" var="error403content"></c:import> 
<c:import url="IndexContent/error404.jsp" var="error404content"></c:import> 
<c:import url="IndexContent/error500.jsp" var="error500content"></c:import> 
<c:import url="IndexContent/findid.jsp" var="findidcontent"></c:import> 
<!--  파라미터에 따라 사이트 제목 정하기 -->
<c:choose>
 <c:when test="${param.page == 1}"><c:set var="titlename" value="에이스도시조형에 오신 것을 환영합니다." /></c:when>
 <c:when test="${param.page == 2}"><c:set var="titlename" value="로 그 인" /></c:when>
 <c:when test="${param.page == 3}"><c:set var="titlename" value="회원 가입" /></c:when> 
 <c:when test="${param.page == 4}"><c:set var="titlename" value="회사소개&연혁" /></c:when> 
 <c:when test="${param.page == 5}"><c:set var="titlename" value="작품" /></c:when> 
 <c:when test="${param.page == 6}"><c:set var="titlename" value="공지사항" /></c:when> 
 <c:when test="${param.page == 7}"><c:set var="titlename" value="자료실" /></c:when> 
 <c:when test="${param.page == 8}"><c:set var="titlename" value="관리자 모드" /></c:when> 
 <c:when test="${param.page == 9}"><c:set var="titlename" value="회원 정보" /></c:when> 
 <c:when test="${param.page == 10}"><c:set var="titlename" value="회원 정보 수정" /></c:when>
 <c:when test="${param.page == 11}"><c:set var="titlename" value="아이디/비밀번호 찾기" /></c:when>
 <c:when test="${param.page == 12}"><c:set var="titlename" value="아이디 찾기" /></c:when>
 <c:when test="${param.page == 13}"><c:set var="titlename" value="비밀번호 찾기" /></c:when>
 <c:when test="${param.page == 403}"><c:set var="titlename" value="403 Forbidden" /></c:when>
 <c:when test="${param.page == 404}"><c:set var="titlename" value="404 Not Found" /></c:when>
 <c:when test="${param.page == 500}"><c:set var="titlename" value="500 Internal Server Error" /></c:when> 
 <c:otherwise><c:set var="titlename" value="에이스도시조형 홈 페이지에 오신 것을 환영합니다. " /></c:otherwise> 
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />	
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./BS/bootstrap.min.css">
<link rel="stylesheet" href="./BS/bootstrap.css">
<!--  Javascript for Hamburger Button -->
<script src="./JS/jquery-3.2.1.slim.min.js" ></script>
<script src="./JS/popper.min.js" ></script>
<script src="./JS/bootstrap.min.js" ></script>
<!--  Span Icon By Google -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<title> <c:out value="${titlename}" /> </title>
<style>
  .jumbotron{
      text-align: center; 
      margin: 0px; 
      padding: 0px; 
      background-color: #f5f5dc; 
  }

  .footer{
      text-align: left; 
      color: white; 
      padding: 10px; 
      margin: 90px;
      background-color: #000080; 
  } 
  body{
      background-color: #f5f5dc; 
  }
</style>
</head>
<body>
<div class="jumbotron" >
   <a href="index.jsp?page=1"><img src="Source/logo.png" style="width: 280px; height: 70px; " /></a> 
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light"> 
   <button class="navbar-toggler" type="button" data-toggle="collapse"
    data-target="#ToggleMenu" aria-controls="ToggleMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon">></span></button>
   
     <div class="collapse navbar-collapse" id="ToggleMenu">
       <div class="navbar-nav">
             <a class="nav-item nav-link active" href="index.jsp?page=4">회사소개&연혁</a>
             <a class="nav-item nav-link active" href="index.jsp?page=15">작가소개</a>
             <a class="nav-item nav-link active" href="index.jsp?page=5">작품</a>
             <a class="nav-item nav-link active" href="index.jsp?page=6">공지사항</a>
             <a class="nav-item nav-link active" href="index.jsp?page=7">자료실</a>
             <c:choose>
             <c:when test="${sessionScope.id eq 'admin'}">
             <a class="nav-item nav-link active" href="index.jsp?page=8">관리자용</a>
             </c:when>
             <c:otherwise>
             </c:otherwise> 
             </c:choose>
       </div> 
       <div class="navbar-nav ml-auto">
            <c:choose>
            <c:when test="${sessionScope.id eq null}">
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='index.jsp?page=2'"><span class="material-symbols-outlined">login</span>로그인</button>&nbsp;&nbsp;
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='./index.jsp?page=3'"><span class="material-symbols-outlined">person_add</span>회원가입</button>
             </c:when>
             <c:otherwise>
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='selectedmember.do'">현재 접속한 사용자: ${sessionScope.id}</button>&nbsp;&nbsp; 
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='logout.do'"><span class="material-symbols-outlined">logout</span>로그아웃</button>
             </c:otherwise>
            </c:choose>
      </div>
</div>
</nav>
<div class="container-fixed ">
     <c:choose>
       <c:when test="${param.page == 1}">
         ${defaultcontent}
       </c:when>
       <c:when test="${param.page == 2}">
         ${logincontent}
       </c:when>
       <c:when test="${param.page == 3}">
         ${signupcontent}
       </c:when> 
       <c:when test="${param.page == 4}">
         ${introcontent}
       </c:when>
       <c:when test="${param.page == 5}">
         ${workscontent}
       </c:when>
       <c:when test="${param.page == 6}">
         
       </c:when>
       <c:when test="${param.page == 7}">
         
       </c:when>
       <c:when test="${param.page == 8}">
       
       </c:when>
       <c:when test="${param.page == 9}">
         ${selectedmembercontent}
       </c:when>
       <c:when test="${param.page == 10}">
         ${modifymembercontent} 
       </c:when>
       <c:when test="${param.page == 11}">
        ${findidandpwcontent}
       </c:when> 
       <c:when test="${param.page == 12}">
        ${findidcontent}
       </c:when>
       <c:when test="${param.page == 13}">
       
       </c:when> 
       <c:when test="${param.page == 14}">
       
       </c:when>
       <c:when test="${param.page == 15}">
       
       </c:when>
       <c:when test="${param.page == 403}">
        ${error403content}
       </c:when>
       <c:when test="${param.page == 404}">
        ${error404content}
       </c:when>
       <c:when test="${parma.page == 500}">
        ${error500content}
       </c:when> 
       <c:otherwise>
         ${defaultcontent}
       </c:otherwise>
     </c:choose>
</div>
<div class="footer">
  <H5> 대표 전화번호:  051-941-3127 </H5>
  <H5> 대표 휴대폰 번호: 010-3596-2140 </H5>
  <H5> 주소: 부산광역시 강서구 미음산단로 327, 303호  </H5>
  <H5> <a href="personalinformation.jsp"> 개인정보처리방침 </a> </H5>
  <br> 
  <H6> Last Updated: 2023-10-28 Created By KYS  </H6> 
  <H6> <a href="technology.jsp"> 웹사이트 제작기술 안내 </a> </H6>
</div>
</body>
</html>