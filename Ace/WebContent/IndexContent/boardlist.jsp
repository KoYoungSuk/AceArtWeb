<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="col-lg-8" style="padding: 10px; margin: 90px; ">
 <H2 style="font-weight: bold; ">자료실</H2>
 <hr> 
 <div style="text-align: right; ">
    <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
    <c:choose>
      <c:when test="${sessionScope.id eq 'admin'}">
          <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=22'"><span class="material-symbols-outlined">create</span>작성</button>
      </c:when>
      <c:otherwise></c:otherwise> 
    </c:choose> 
    <button class="btn btn-secondary btn-sm" onclick="location.href='totalboardlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
 </div> 
 <hr> 
 <div style="text-align: center;">
          <H4 style="font-weight: bold;">
          <c:choose>
          <c:when test="${pagecount ne 1}"> <!-- 첫번째 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalboardlist.do?page_count=${pagecount - 1}'"><span class="material-symbols-outlined">arrow_back_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise>
          </c:choose>
          &nbsp;&nbsp;&nbsp;&nbsp;
          CURRENT PAGE : PAGE ${pagecount} 
          &nbsp;&nbsp;&nbsp;&nbsp;
          <c:choose> 
          <c:when test="${pagecount ne sessionScope.pagenum_board}"> <!-- 마지막 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalboardlist.do?page_count=${pagecount + 1}'"><span class="material-symbols-outlined">arrow_forward_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise> 
          </c:choose>
          </H4> 
 </div> 
 <hr> 
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
     <c:forEach var="BoardDTO" items="${sessionScope.totalboardlist}" begin="${sessionScope.beginnumber_board}" end="${sessionScope.endnumber_board}">
      <tr>
       <td><c:out value="${BoardDTO.num}" /></td>
       <td><a href="detailboardlist.do?num=${BoardDTO.num}">${BoardDTO.title}</a></td>
       <td><c:out value="${BoardDTO.savedate}" /></td>
       <td><c:out value="${BoardDTO.modifydate}" /></td>
       <td>
       <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='deleteboardlist.do?num=${BoardDTO.num}'"><span class="material-symbols-outlined">delete</span></button>     
      </tr>
     </c:forEach>
   </tbody>
 </table>
 <div style="text-align: center;">
    <c:forEach var="num" begin="1" end="${sessionScope.pagenum_board}">
      <button type="button" class="btn btn-secondary" onclick="location.href='totalboardlist.do?page_count=${num}'">${num}</button>
    </c:forEach>
 </div>
</div>
</div>