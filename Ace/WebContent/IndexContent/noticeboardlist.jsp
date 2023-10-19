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
     <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=17'"><span class="material-symbols-outlined">create</span>작성(관리자용)</button>
     </c:when>
     <c:otherwise></c:otherwise> 
     </c:choose>
     <button class="btn btn-secondary btn-sm" onclick="location.href='noticeboardlist.do?desc=0'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
  </div> 
  <hr> 
  <div style="text-align: right;">
   <form action="searchnotice.do" method="POST">
    <input type="text" class="form-control-sm" name="word" placeholder="Search Title" value="" />
    <button type="submit" class="btn btn-secondary btn-sm"><span class="material-symbols-outlined">search</span>검색</button>
    <c:choose>
     <c:when test="${param.desc == 1}">
         <button type="button" class="btn btn-primary btn-sm" onclick="location.href='noticeboardlist.do?desc=0'"><span class="material-symbols-outlined">trending_up</span>오름차순으로 정렬</button>
     </c:when>
     <c:otherwise>
         <button type="button" class="btn btn-danger btn-sm" onclick="location.href='noticeboardlist.do?desc=1'"><span class="material-symbols-outlined">trending_down</span>내림차순으로 정렬</button> 
     </c:otherwise>
   </c:choose>
   </form>
  </div> 
  <hr>
  <table class="table">
   <thead>
     <tr>
       <th>번호</th>
       <th>제목</th>
       <th>작성 날짜</th>
       <th>수정 날짜</th> 
       <th>삭제</th> 
     </tr>
   </thead>
   <tbody>
   <c:forEach var="NoticeDTO" items="${sessionScope.noticeboardlist}" varStatus="status">
     <tr>
       <td><c:out value="${NoticeDTO.num}" /></td>
       <td><a href="noticeboarddetail.do?num=${NoticeDTO.num}"><c:out value="${NoticeDTO.title}" /></a>
       <td><c:out value="${NoticeDTO.savedate}" /></td>
       <td><c:out value="${NoticeDTO.modifydate}" /></td>
       <td><a href="deletenoticeboard.do?num=${NoticeDTO.num}">삭제</a></td> 
     </tr>
   </c:forEach>
   </tbody>
  </table>
</div> 


</div>