<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
  <H2 style="font-weight: bold;">질문 삭제</H2>
  <hr>
  <H6>경고: 삭제하면 다시 복구할 수 없습니다. 계속하시겠습니까? </H6>
  <H6>만약 답변이 있는 질문을 삭제한다면 답변까지 삭제됩니다. </H6>
  <hr> 
  <form action="deletequestion.do" method="POST">
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <button type="submit" class="btn btn-danger btn-sm" ><span class="material-symbols-outlined">delete</span>삭제</button>
  </div> 
 <table class="table">
 <tbody>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">번호</td>
  <td>
  <input type="hidden" name="num" value="${sessionScope.detailquestionlist['num']}" /> 
  <input type="hidden" name="user" value="${sessionScope.detailquestionlist['user']}" /> 
  ${sessionScope.detailquestionlist["num"]}
  </td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">질문명</td>
  <td>${sessionScope.detailquestionlist["title"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">질문 내용</td>
  <td>${sessionScope.detailquestionlist["content"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">접근 모드</td>
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
  <td style="font-weight: bold; font-size: 20px;">작성 날짜</td>
  <td>${sessionScope.detailquestionlist["savedate"]}</td>
  </tr> 
  <tr>
  <td style="font-weight: bold; font-size: 20px;">수정 날짜</td>
  <td>${sessionScope.detailquestionlist["modifydate"]}</td> 
  </tr> 
 </tbody>
</table>
</form>
</div>
</div>