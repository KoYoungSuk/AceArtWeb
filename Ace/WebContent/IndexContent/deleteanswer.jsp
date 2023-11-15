<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
<c:choose>
<c:when test="${sessionScope.id eq 'admin'}">
<form action="deleteanswer.do" method="POST">
  <H2 style="font-weight: bold;">답변 삭제</H2>
  <hr>
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <button type="submit" class="btn btn-danger btn-sm" ><span class="material-symbols-outlined">delete</span>삭제</button>
  </div> 
 <table class="table">
 <tbody>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">질문 번호</td>
  <td>${sessionScope.detailanswerlist["q_num"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">답변 번호</td>
  <td>
  ${sessionScope.detailanswerlist["num"]}
  <input type="hidden" name="num" value="${sessionScope.detailanswerlist['num']}" /> 
  </td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">답변명</td>
  <td>${sessionScope.detailanswerlist["title"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">답변 내용</td>
  <td>${sessionScope.detailanswerlist["content"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">접근 모드</td>
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
  <td style="font-weight: bold; font-size: 20px;" >작성 날짜</td>
  <td>${sessionScope.detailanswerlist["savedate"]}</td>
  </tr> 
  <tr>
  <td style="font-weight: bold; font-size: 20px;">수정 날짜</td>
  <td>${sessionScope.detailanswerlist["modifydate"]}</td> 
  </tr> 
 </tbody>
</table>
</form>
</c:when>
<c:otherwise>
  <script>
     alert("관리자만 답변 삭제가 가능합니다.");
     history.go(-1); 
  </script>
</c:otherwise> 
</c:choose>
</div>
</div>