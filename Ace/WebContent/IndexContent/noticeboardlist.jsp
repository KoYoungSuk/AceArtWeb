<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:choose>
  <c:when test="${param.page_count eq null}">
    <c:set var="pagecount" value="1" /> 
  </c:when>
  <c:otherwise>
   <c:set var="pagecount" value="${param.page_count}" /> 
  </c:otherwise> 
</c:choose> 
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold; ">공지사항</H2>
  <div style="text-align: right; ">
     <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose> 
     <c:when test="${sessionScope.id eq 'admin'}"> <!-- 관리자 모드로 로그인했을 때  -->
     <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=17'"><span class="material-symbols-outlined">create</span>작성(관리자용)</button>
     </c:when>
     <c:otherwise></c:otherwise> 
     </c:choose>
     <button class="btn btn-secondary btn-sm" onclick="location.href='noticeboardlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
  </div> 
  <hr> 
  <!-- 
  <div style="text-align: right;">
   <form action="searchnotice.do" method="POST">
    <input type="text" class="form-control-sm" name="word" placeholder="Search Title" value="" />
    <button type="submit" class="btn btn-secondary btn-sm"><span class="material-symbols-outlined">search</span>검색</button>
   </form>
  </div> 
  --> 
  <hr>
  <div style="text-align: center;">
          <H4 style="font-weight: bold;">
          <c:choose>
          <c:when test="${pagecount ne 1}"> <!-- 첫번째 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='noticeboardlist.do?page_count=${pagecount - 1}'"><span class="material-symbols-outlined">arrow_back_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise>
          </c:choose>
          &nbsp;&nbsp;&nbsp;&nbsp;
          CURRENT PAGE : PAGE ${pagecount} 
          &nbsp;&nbsp;&nbsp;&nbsp;
          <c:choose> 
          <c:when test="${pagecount ne sessionScope.pagenum_notice}"> <!-- 마지막 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='noticeboardlist.do?page_count=${pagecount + 1}'"><span class="material-symbols-outlined">arrow_forward_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise> 
          </c:choose>
          </H4> 
  </div> 
  <table class="table">
   <thead>
     <tr>
       <th></th>
       <th>제목</th>
       <th>작성 날짜</th>
       <th>수정 날짜</th> 
     </tr>
   </thead>
   <tbody>
   <c:forEach var="NoticeDTO" items="${sessionScope.noticeboardlist}" begin="${sessionScope.beginnumber_notice}" end="${sessionScope.endnumber_notice}" >
     <tr>
       <td><c:out value="${NoticeDTO.num}" /></td>
       <td><a href="noticeboarddetail.do?num=${NoticeDTO.num}"><c:out value="${NoticeDTO.title}" /></a>
       <td><c:out value="${NoticeDTO.savedate}" /></td>
       <td><c:out value="${NoticeDTO.modifydate}" /></td>
       <td>
        <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='deletenoticeboard.do?num=${NoticeDTO.num}'"><span class="material-symbols-outlined">delete</span></button>
       </td> 
     </tr>
   </c:forEach>
   </tbody>
  </table>
  <div style="text-align: center;">
    <c:forEach var="num" begin="1" end="${sessionScope.pagenum_notice}">
      <c:choose>
      <c:when test="${pagecount == num}">
      <button type="button" class="btn btn-danger" onclick="location.href='noticeboardlist.do?page_count=${num}'">${num}</button>
      </c:when>
      <c:otherwise>
      <button type="button" class="btn btn-secondary" onclick="location.href='noticeboardlist.do?page_count=${num}'">${num}</button>
      </c:otherwise>
      </c:choose>
    </c:forEach>
   </div>
</div> 
</div>