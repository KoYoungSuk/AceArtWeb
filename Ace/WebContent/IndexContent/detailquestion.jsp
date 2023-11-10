<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold;">질문 상세</H2>
  <hr>
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <c:choose> 
   <c:when test="${sessionScope.id eq sessionScope.user}"> 
   <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='modifyquestion.do?num=${sessionScope.detailquestionlist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
   <button type="button" class="btn btn-danger btn-sm" onclick="location.href='deletequestion.do?num=${sessionScope.detailquestionlist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
   <c:choose>
   <c:when test="${sessionScope.answercount == 0}">
   <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=38'"><span class="material-symbols-outlined">create</span>답변하기</button>'
   </c:when>
   <c:otherwise></c:otherwise> 
   </c:choose>
  </c:when> 
  <c:otherwise></c:otherwise> 
   </c:choose>
  </div> 
 <table class="table">
 <tbody>
  <tr>
  <td>번호</td>
  <td>${sessionScope.detailquestionlist["num"]}</td> 
  </tr>
  <tr>
  <td>질문명</td>
  <td>${sessionScope.detailquestionlist["title"]}</td> 
  </tr>
  <tr>
  <td>질문 내용</td>
  <td>${sessionScope.detailquestionlist["content"]}</td> 
  </tr>
  <tr>
  <td>접근 모드</td>
  <td>
  <c:choose>
  <c:when test="${sessionScope.detailquestionlist['access'] eq 'secret'}">
     비공개 모드(관리자 및 작성자만 확인 가능)
  </c:when>
  <c:otherwise>
    공개 모드
  </c:otherwise> 
  </c:choose> 
  </td> 
  </tr>
  <tr>
  <td>작성 날짜</td>
  <td>${sessionScope.detailquestionlist["savedate"]}</td>
  </tr> 
  <tr>
  <td>수정 날짜</td>
  <td>${sessionScope.detailquestionlist["modifydate"]}</td> 
  </tr> 
 </tbody>
</table>
</div>
</div>