<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-xl-4" style="margin: 90px; padding: 10px;  "> 
  <c:choose>
  <c:when test="${sessionScope.id eq null}">
  <H2 style="font-weight: bold; ">아이디/비밀번호 찾기</H2>
  <hr> 
  <div style="text-align: center;">
  <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);" ><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
  &nbsp;&nbsp; 
  <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=12'"><span class="material-symbols-outlined">search</span>아이디 찾기 </button>
  &nbsp;&nbsp; 
  <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=13'"><span class="material-symbols-outlined">search</span>비밀번호 찾기 </button> 
  </div>
  </c:when>
  <c:otherwise>
  <script>
     alert("아이디 및 비밀번호 찾기 기능은 로그인되어 있을때는 사용할 수 없습니다.");
     history.go(-1); 
  </script>
  </c:otherwise>
  </c:choose>
</div>
</div>