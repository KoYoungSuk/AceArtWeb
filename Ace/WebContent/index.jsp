<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!--  JSP 모듈 파일 임포트 -->
<c:import url="IndexContent/default.jsp" var="defaultcontent"></c:import> 
<c:import url="IndexContent/login.jsp" var="logincontent"></c:import> 
<c:import url="IndexContent/signup.jsp" var="signupcontent"></c:import> 
<c:import url="IndexContent/introduction.jsp" var="introcontent"></c:import> 
<c:import url="IndexContent/works.jsp" var="workscontent"></c:import>
<c:import url="IndexContent/writeworks.jsp" var="writeworkscontent"></c:import>
<c:import url="IndexContent/detailworks.jsp" var="detailworkscontent"></c:import> 
<c:import url="IndexContent/modifyworks.jsp" var="modifyworkscontent"></c:import>
<c:import url="IndexContent/deleteworks.jsp" var="deleteworkscontent"></c:import> 
<c:import url="IndexContent/selectedmember.jsp" var="selectedmembercontent"></c:import>
<c:import url="IndexContent/findidandpw.jsp" var="findidandpwcontent"></c:import>
<c:import url="IndexContent/modifymember.jsp" var="modifymembercontent"></c:import> 
<c:import url="IndexContent/deletemember.jsp" var="deletemembercontent"></c:import> 
<c:import url="IndexContent/admin.jsp" var="admincontent"></c:import> 
<c:import url="IndexContent/error403.jsp" var="error403content"></c:import> 
<c:import url="IndexContent/error404.jsp" var="error404content"></c:import> 
<c:import url="IndexContent/error500.jsp" var="error500content"></c:import> 
<c:import url="IndexContent/findid.jsp" var="findidcontent"></c:import> 
<c:import url="IndexContent/findpw.jsp" var="findpwcontent"></c:import> 
<c:import url="IndexContent/changepw.jsp" var="changepwcontent"></c:import> 
<c:import url="IndexContent/totalmemberlist.jsp" var="totalmembercontent"></c:import> 
<c:import url="IndexContent/artist.jsp" var="artistcontent"></c:import> 
<c:import url="IndexContent/writeartist.jsp" var="writeartistcontent"></c:import> 
<c:import url="IndexContent/detailartist.jsp" var="detailartistcontent"></c:import> 
<c:import url="IndexContent/modifyartist.jsp" var="modifyartistcontent"></c:import> 
<c:import url="IndexContent/noticeboardlist.jsp" var="noticeboardcontent"></c:import> 
<c:import url="IndexContent/detailnoticeboard.jsp" var="detailnoticecontent"></c:import> 
<c:import url="IndexContent/deletenoticeboard.jsp" var="deletenoticecontent"></c:import> 
<c:import url="IndexContent/modifynoticeboard.jsp" var="modifynoticecontent"></c:import> 
<c:import url="IndexContent/boardlist.jsp" var="boardlistcontent"></c:import> 
<c:import url="IndexContent/writeboard.jsp" var="writeboardcontent"></c:import> 
<c:import url="IndexContent/detailboard.jsp" var="detailboardcontent"></c:import> 
<c:import url="IndexContent/deleteboard.jsp" var="deleteboardcontent"></c:import> 
<c:import url="IndexContent/modifyboard.jsp" var="modifyboardcontent"></c:import> 
<c:import url="IndexContent/writenotice.jsp" var="writenoticecontent"></c:import> 
<c:import url="IndexContent/question.jsp" var="questioncontent"></c:import> 
<c:import url="IndexContent/writequestion.jsp" var="writequestioncontent"></c:import> 
<c:import url="IndexContent/detailquestion.jsp" var="detailquestioncontent"></c:import> 
<c:import url="IndexContent/modifyquestion.jsp" var="modifyquestioncontent"></c:import> 
<c:import url="IndexContent/deletequestion.jsp" var="deletequestioncontent"></c:import> 
<c:import url="IndexContent/writeanswer.jsp" var="writeanswercontent"></c:import> 
<c:import url="IndexContent/detailanswer.jsp" var="detailanswercontent"></c:import> 
<c:import url="IndexContent/modifyanswer.jsp" var="modifyanswercontent"></c:import> 
<c:import url="IndexContent/deleteanswer.jsp" var="deleteanswercontent"></c:import> 
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
 <c:when test="${param.page == 14}"><c:set var="titlename" value="비밀번호 변경" /></c:when> 
 <c:when test="${param.page == 15}"><c:set var="titlename" value="작가 소개 " /></c:when>
 <c:when test="${param.page == 16}"><c:set var="titlename" value="회원정보 삭제" /></c:when> 
 <c:when test="${param.page == 17}"><c:set var="titlename" value="공지사항 추가" /></c:when> 
 <c:when test="${param.page == 18}"><c:set var="titlename" value="공지사항" /></c:when> 
 <c:when test="${param.page == 19}"><c:set var="titlename" value="공지사항 삭제" /></c:when>
 <c:when test="${param.page == 20}"><c:set var="titlename" value="공지사항 수정" /></c:when> 
 <c:when test="${param.page == 21}"><c:set var="titlename" value="전체 회원정보 관리" /></c:when> 
 <c:when test="${param.page == 22}"><c:set var="titlename" value="자료실 등록" /></c:when>
 <c:when test="${param.page == 23}"><c:set var="titlename" value="자료실" /></c:when> 
 <c:when test="${param.page == 24}"><c:set var="titlename" value="자료실 삭제" /></c:when> 
 <c:when test="${param.page == 25}"><c:set var="titlename" value="자료실 수정" /></c:when> 
 <c:when test="${param.page == 26}"><c:set var="titlename" value="작품 등록" /></c:when> 
 <c:when test="${param.page == 27}"><c:set var="titlename" value="작품 상세정보" /></c:when> 
 <c:when test="${param.page == 28}"><c:set var="titlename" value="작품 수정" /></c:when>
 <c:when test="${param.page == 29}"><c:set var="titlename" value="작품 삭제" /></c:when> 
 <c:when test="${param.page == 30}"><c:set var="titlename" value="작가 추가" /></c:when> 
 <c:when test="${param.page == 31}"><c:set var="titlename" value="작가 세부 정보" /></c:when> 
 <c:when test="${param.page == 32}"><c:set var="titlename" value="작가 정보 수정" /></c:when> 
 <c:when test="${param.page == 33}"><c:set var="titlename" value="Question&Answer" /></c:when> 
 <c:when test="${param.page == 34}"><c:set var="titlename" value="질문하기" /></c:when> 
 <c:when test="${param.page == 35}"><c:set var="titlename" value="질문 상세" /></c:when> 
 <c:when test="${param.page == 36}"><c:set var="titlename" value="질문 수정" /></c:when> 
 <c:when test="${param.page == 37}"><c:set var="titlename" value="질문 삭제" /></c:when>
 <c:when test="${param.page == 38}"><c:set var="titlename" value="답변하기" /></c:when>
 <c:when test="${param.page == 39}"><c:set var="titlename" value="답변 상세" /></c:when>
 <c:when test="${param.page == 40}"><c:set var="titlename" value="답변 수정" /></c:when> 
 <c:when test="${param.page == 41}"><c:set var="titlename" value="답변 삭제" /></c:when> 
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
<!-- Mobile Friendly Meta -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./BS/bootstrap.min.css">
<link rel="stylesheet" href="./BS/bootstrap.css">
<!--  Javascript for Hamburger Button -->
<script src="./JS/jquery-3.2.1.slim.min.js" ></script>
<script src="./JS/popper.min.js" ></script>
<script src="./JS/bootstrap.min.js" ></script>
<!--  Span Icon By Google -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<title> <c:out value="${titlename}" />  | 에이스도시조형 </title>
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
    <a class="nav-item navbar-brand" href="./index.jsp?page=1"><img src="./Source/logo_lesser.png" width="45" height="45" /></a>
    <!-- Hamburger Button -->
   <button class="navbar-toggler" type="button" data-toggle="collapse"
    data-target="#ToggleMenu" aria-controls="ToggleMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon">></span></button>
   
     <div class="collapse navbar-collapse" id="ToggleMenu">
       <div class="navbar-nav">
             <a class="nav-item nav-link active" href="index.jsp?page=4">회사소개&연혁</a>
             <a class="nav-item nav-link active">|</a>
             <a class="nav-item nav-link active" href="totalartistlist.do?desc=0">작가소개</a>
             <a class="nav-item nav-link active">|</a>
             <a class="nav-item nav-link active" href="workslist.do?desc=0">작품</a>
             <a class="nav-item nav-link active">|</a>
             <a class="nav-item nav-link active" href="noticeboardlist.do?desc=0">공지사항</a>
             <a class="nav-item nav-link active">|</a>
             <a class="nav-item nav-link active" href="totalboardlist.do?desc=0">자료실</a> 
             <a class="nav-item nav-link active">|</a>
             <a class="nav-item nav-link active" href="totalquestionlist.do">Q&A</a>
       </div> 
       <div class="navbar-nav ml-auto">
            <c:choose>
            <c:when test="${sessionScope.id eq null}"> <!-- 로그인하지 않았을 때  -->
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='index.jsp?page=2'"><span class="material-symbols-outlined">login</span>로그인</button>&nbsp;&nbsp;
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='./index.jsp?page=3'"><span class="material-symbols-outlined">person_add</span>회원가입</button>
             </c:when>
             <c:otherwise>
             <c:choose>
              <c:when test="${sessionScope.id eq 'admin'}">
                <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='index.jsp?page=8'"><span class="material-symbols-outlined">lock</span>관리자 모드</button>&nbsp;&nbsp;
              </c:when>
              <c:otherwise></c:otherwise> 
             </c:choose> 
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='selectedmember.do'">현재 접속한 사용자: ${sessionScope.id}</button>&nbsp;&nbsp; 
             <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='logout.do'"><span class="material-symbols-outlined">logout</span>로그아웃</button>
             </c:otherwise>
            </c:choose>
      </div>
</div>
</nav>
<div class="container-fixed ">
     <c:choose>
       <c:when test="${param.page == 1}"> <!-- 메인화면 -->
         ${defaultcontent}
       </c:when>
       <c:when test="${param.page == 2}"> <!-- 로그인  -->
         ${logincontent}
       </c:when>
       <c:when test="${param.page == 3}"> <!-- 회원가입 -->
         ${signupcontent}
       </c:when> 
       <c:when test="${param.page == 4}"> <!-- 소개  -->
         ${introcontent}
       </c:when>
       <c:when test="${param.page == 5}"> <!-- 작품 게시판  -->
         ${workscontent}
       </c:when>
       <c:when test="${param.page == 6}"> <!-- 공지사항 게시판 -->
         ${noticeboardcontent}
       </c:when>
       <c:when test="${param.page == 7}"> <!-- 자료실 게시판  -->
         ${boardlistcontent}
       </c:when>
       <c:when test="${param.page == 8}"> <!-- 관리자용 -->
        ${admincontent}
       </c:when>
       <c:when test="${param.page == 9}"> <!-- 회원정보 출력  -->
         ${selectedmembercontent}
       </c:when>
       <c:when test="${param.page == 10}"> <!-- 회원정보 수정 -->
         ${modifymembercontent} 
       </c:when>
       <c:when test="${param.page == 11}"> <!-- 아이디/비밀번호 찾기 -->
        ${findidandpwcontent}
       </c:when> 
       <c:when test="${param.page == 12}"> <!--  아이디 찾기 -->
        ${findidcontent}
       </c:when>
       <c:when test="${param.page == 13}"> <!-- 비밀번호 찾기 -->
        ${findpwcontent}
       </c:when> 
       <c:when test="${param.page == 14}"> <!-- 비밀번호 변경 -->
        ${changepwcontent}
       </c:when>
       <c:when test="${param.page == 15}"> <!--  작가소개  -->
        ${artistcontent}
       </c:when>
       <c:when test="${param.page == 16}"> <!-- 회원정보 삭제  -->
        ${deletemembercontent}
       </c:when> 
       <c:when test="${param.page == 17}"> <!-- 공지사항 추가 -->
        ${writenoticecontent}
       </c:when>
       <c:when test="${param.page == 18}"> <!-- 공지사항 세부정보 -->
        ${detailnoticecontent}
       </c:when>
       <c:when test="${param.page == 19}"> <!-- 공지사항 삭제  -->
        ${deletenoticecontent}
       </c:when>
       <c:when test="${param.page == 20}"> <!-- 공지사항 수정  -->
        ${modifynoticecontent}
       </c:when>
       <c:when test="${param.page == 21}"> <!-- 전체 회원정보 관리 -->
        ${totalmembercontent}
       </c:when>
       <c:when test="${param.page == 22}"> <!-- 자료실 등록  -->
        ${writeboardcontent}
       </c:when> 
       <c:when test="${param.page == 23}"> <!--  자료실 세부정보  -->
        ${detailboardcontent}
       </c:when>
       <c:when test="${param.page == 24}"> <!--  자료실 정보 삭제 -->
        ${deleteboardcontent}
       </c:when> 
       <c:when test="${param.page == 25}"> <!-- 자료실 정보 수정  -->
        ${modifyboardcontent}
       </c:when> 
       <c:when test="${param.page == 26}"> <!--  작품 등록 --> 
        ${writeworkscontent} 
       </c:when> 
       <c:when test="${param.page == 27}"> <!--  작품 상세정보 --> 
        ${detailworkscontent}
       </c:when> 
       <c:when test="${param.page == 28}"> <!-- 작품 수정 -->
        ${modifyworkscontent}
       </c:when>
       <c:when test="${param.page == 29}"> <!-- 작품 삭제 -->
        ${deleteworkscontent}
       </c:when>
       <c:when test="${param.page == 30}"> <!-- 작가 정보 등록 -->
        ${writeartistcontent}
       </c:when> 
       <c:when test="${param.page == 31}"> <!-- 작가 정보 확인 -->
        ${detailartistcontent}
       </c:when> 
       <c:when test="${param.page == 32}"> <!-- 작가 정보 수정 -->
        ${modifyartistcontent}
       </c:when> 
       <c:when test="${param.page == 33}"> <!-- 질문과 답변 게시판 -->
        ${questioncontent}
       </c:when> 
       <c:when test="${param.page == 34}"> <!-- 질문하기 -->
        ${writequestioncontent}
       </c:when> 
       <c:when test="${param.page == 35}"> <!-- 질문 상세 -->
        ${detailquestioncontent}
       </c:when>
       <c:when test="${param.page == 36}"> <!-- 질문 수정 -->
        ${modifyquestioncontent}
       </c:when>
       <c:when test="${param.page == 37}"> <!-- 질문 삭제 -->
        ${deletequestioncontent}
       </c:when>
       <c:when test="${param.page == 38}"> <!-- 답변하기 -->
        ${writeanswercontent}
       </c:when>
       <c:when test="${param.page == 39}"> <!-- 답변 상세 -->
        ${detailanswercontent}
       </c:when>
       <c:when test="${param.page == 40}"> <!-- 답변 수정 -->
        ${modifyanswercontent}
       </c:when> 
       <c:when test="${param.page == 41}"> <!-- 답변 삭제  -->
        ${deleteanswercontent}
       </c:when> 
       <c:when test="${param.page == 403}"> <!-- Error 403 -->
        ${error403content}
       </c:when>
       <c:when test="${param.page == 404}"> <!-- Error 404 -->
        ${error404content}
       </c:when>
       <c:when test="${param.page == 500}"> <!-- Error 500 -->
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
  <H5> <a href="pdf.jsp?pdfname=personalinformation.pdf"> 개인정보처리방침 </a> </H5>
  <br> 
  <H6> Last Updated: Sunday, November 12nd, 2023 </H6> 
  <H6> <a href="pdf.jsp?pdfname=Environment.pdf"> 웹사이트 제작기술 안내 </a> </H6>
  <H6> <a href= "https://github.com/KoYoungSuk/AceArtWeb.git">GitHub</a></H6>
</div>
</body>
</html>