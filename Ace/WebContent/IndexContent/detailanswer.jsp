<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold;">답변 상세</H2>
  <hr>
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <c:choose> 
   <c:when test="${sessionScope.id eq 'admin'}"> 
   <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='modifyanswer.do?num=${sessionScope.detailanswerlist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
   <button type="button" class="btn btn-danger btn-sm" onclick="location.href='deleteanswer.do?num=${sessionScope.detailanswerlist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
   </c:when> 
  <c:otherwise>
   <button type="button" class="btn btn-secondary btn-sm" onclick="location.href=''"><span class="material-symbols-outlined">create</span>답변하기</button>
  </c:otherwise> 
   </c:choose>
  </div> 
 <table class="table">
 <tbody>
  <tr>
  <td>질문 번호</td>
  <td>${sessionScope.detailanswerlist["q_num"]}</td> 
  </tr>
  <tr>
  <td>답변 번호</td>
  <td>${sessionScope.detailanswerlist["num"]}</td> 
  </tr>
  <tr>
  <td>답변명</td>
  <td>${sessionScope.detailanswerlist["title"]}</td> 
  </tr>
  <tr>
  <td>답변 내용</td>
  <td>${sessionScope.detailanswerlist["content"]}</td> 
  </tr>
  <tr>
  <td>접근 모드</td>
  <td>
  <c:choose>
  <c:when test="${sessionScope.detailanswerlist['access'] eq 'secret'}">
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
  <td>${sessionScope.detailanswerlist["savedate"]}</td>
  </tr> 
  <tr>
  <td>수정 날짜</td>
  <td>${sessionScope.detailanswerlist["modifydate"]}</td> 
  </tr> 
 </tbody>
</table>
</div>
</div>