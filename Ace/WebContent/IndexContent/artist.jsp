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
<div class="col-lg-8" style="padding: 90px; margin: 10px;">
  <H2 style="font-weight: bold;">작가 소개  </H2> 
  <hr>
  <div style="text-align: right; ">
     <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
     <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=30'"><span class="material-symbols-outlined">create</span>추가(관리자용)</button>'
     </c:when> 
     <c:otherwise></c:otherwise> 
     </c:choose>
     <button class="btn btn-secondary btn-sm" onclick="location.href='artistlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
  </div> 
  <div style="text-align: center;">
          <H4 style="font-weight: bold;">
          <c:choose>
          <c:when test="${pagecount ne 1}"> <!-- 첫번째 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalartistlist.do?page_count=${pagecount - 1}'"><span class="material-symbols-outlined">arrow_back_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise>
          </c:choose>
          &nbsp;&nbsp;&nbsp;&nbsp;
          CURRENT PAGE : PAGE ${pagecount} 
          &nbsp;&nbsp;&nbsp;&nbsp;
          <c:choose> 
          <c:when test="${pagecount ne sessionScope.pagenum_artist}"> <!-- 마지막 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalartistlist.do?page_count=${pagecount + 1}'"><span class="material-symbols-outlined">arrow_forward_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise> 
          </c:choose>
          </H4> 
  </div> 
  <hr>
  
  <table class="table">
    <c:forEach var="artistDTO" items="${sessionScope.totalartistlist}" begin="${sessionScope.beginnumber_artist}" end="${sessionScope.endnumber_artist}">
      <tr>
       <td style="font-weight: bold; font-size: 20px;">작가 이름</td>
       <td><c:out value="${artistDTO.name}" /></td> 
      </tr>
      <tr>
       <td style="font-weight: bold; font-size: 20px;">작가 경력</td>
       <td><c:out value="${artistDTO.career}" /></td> 
      </tr>
      <tr>
       <td style="font-weight: bold; font-size: 20px;">작품</td>
       <td>
         <img src="/Artist/${artistDTO.num}/${artistDTO.work1}" width="320" height="270" />&nbsp;&nbsp;
         <img src="/Artist/${artistDTO.num}/${artistDTO.work2}" width="320" height="270" /> 
       </td> 
      </tr> 
      <tr>
       <td style="font-weight: bold; font-size: 20px;"></td>
       <td>
           <button class="btn btn-secondary btn-sm" onclick="location.href='detailartistlist.do?num=${artistDTO.num}'"><span class="material-symbols-outlined">details</span>자세히 보기</button>
           <c:choose>
           <c:when test="${sessionScope.id eq 'admin'}">
           <button class="btn btn-secondary btn-sm" onclick="location.href='modifyartist.do?num=${artistDTO.num}'"><span class="material-symbols-outlined">create</span>수정</button>
           <button class="btn btn-secondary btn-sm" onclick="location.href='deleteartist.do?num=${artistDTO.num}'"><span class="material-symbols-outlined">delete</span>삭제</button>
           </c:when>
           <c:otherwise></c:otherwise>
           </c:choose> 
       </td> 
     </tr>
    </c:forEach>
  </table>
  <div style="text-align: center;">
    <c:forEach var="num" begin="1" end="${sessionScope.pagenum_artist}">\
     <c:choose>
     <c:when test="${pagecount == num }">
      <button type="button" class="btn btn-danger" onclick="location.href='totalartistlist.do?page_count=${num}'">${num}</button>
     </c:when>
     <c:otherwise>
      <button type="button" class="btn btn-secondary" onclick="location.href='totalartistlist.do?page_count=${num}'">${num}</button>
     </c:otherwise>
     </c:choose>
    </c:forEach>
  </div>
</div>
</div>