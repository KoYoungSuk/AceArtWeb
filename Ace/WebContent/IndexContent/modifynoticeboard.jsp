<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px; ">
<c:choose>
  <c:when test="${sessionScope.id eq 'admin'}"> <!-- 관리자 모드로 로그인했을 때 -->
    <H2 style="font-weight: bold; ">공지사항 수정 </H2>
    <hr>
    <form action="modifynoticeboard.do" method="POST">
    <div style="text-align: right;">
       <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>&nbsp;&nbsp; 
       <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>수정 </button>
    </div> 
    <table class="table">
      <tr>
        <td><label for="title">번호</label></td>
        <td><input type="hidden" name="num" value="${sessionScope.detailnoticelist['num']}" />${sessionScope.detailnoticelist['num']}</td> 
      </tr>
      <tr>
        <td><label for="title">제목</label></td>
        <td><input class="form-control mr-sm-10" type="text" name="title" required value="${sessionScope.detailnoticelist['title']}"/></td> 
      </tr>
      <tr>
        <td><label for="content">내용</label></td>
        <td><textarea class="form-control mr-sm-10" rows="20" cols="100" autofocus name="content" wrap="hard" >${sessionScope.detailnoticelist['content']}</textarea></td>
      </tr>
      <tr>
       <td><label for="savedate">작성 날짜</label></td>
       <td>${sessionScope.detailnoticelist['savedate']}</td>
      </tr>
      <tr>
       <td><label for="modifydate">수정 날짜</label></td>
       <td>${sessionScope.detailnoticelist['modifydate']}</td> 
      </tr> 
    </table>
    </form> 
  </c:when>
  <c:otherwise>
    <p>관리자 계정으로만 공지사항 수정이 가능합니다. </p> 
  </c:otherwise> 
</c:choose>
</div> 
</div>