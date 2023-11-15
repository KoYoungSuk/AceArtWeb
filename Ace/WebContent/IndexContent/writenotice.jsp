<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px; ">
<c:choose>
  <c:when test="${sessionScope.id eq 'admin'}"> <!-- 관리자 모드로 로그인했을 때 -->
    <H2 style="font-weight: bold; ">공지사항 추가</H2>
    <hr>
    <form action="writenotice.do" method="POST">
    <div style="text-align: right;">
       <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>&nbsp;&nbsp; 
       <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>추가 </button>
    </div> 
    <table class="table">
      <tr>
        <td><label for="title" style="font-weight: bold; font-size: 20px;">제목: </label></td>
        <td><input class="form-control mr-sm-10" type="text" name="title" required /></td> 
      </tr>
      <tr>
        <td><label for="content" style="font-weight: bold; font-size: 20px;">내용: </label></td>
        <td><textarea class="form-control mr-sm-10" rows="20" cols="100" autofocus name="content" wrap="hard" ></textarea></td>
      </tr>
    </table>
    </form> 
  </c:when>
  <c:otherwise>
    <script>
    alert("관리자 계정으로만 공지사항 추가가 가능합니다.");
    history.go(-1); 
    </script>
  </c:otherwise> 
</c:choose>
</div> 
</div>