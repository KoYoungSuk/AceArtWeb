<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold; ">공지사항</H2>
  <hr> 
  <div style="text-align: right; ">
     <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose> 
     <c:when test="${sessionScope.id eq 'admin'}"> <!-- 관리자 모드로 로그인했을 때  -->
     <button class="btn btn-secondary btn-sm" onclick="location.href=''"><span class="material-symbols-outlined">create</span>작성(관리자용)</button>
     </c:when>
     <c:otherwise></c:otherwise> 
     </c:choose>
     <button class="btn btn-secondary btn-sm" onclick="location.href='noticeboardlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
  </div> 
</div> 
<div style="text-align: center;">
  <form action="searchnotice.do" method="POST">
    <input type="text" class="form-control-sm" name="word" placeholder="Search Title" value="" />
    <button type="submit" class="btn btn-secondary btn-sm"><span class="material-symbols-outlined">search</span>검색</button>
  </form>
</div> 

</div>