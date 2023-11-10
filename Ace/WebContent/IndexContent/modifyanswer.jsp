<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
<c:choose>
<c:when test="${sessionScope.id eq 'admin'}">
 <H2 style="font-weight: bold;">답변 수정</H2>
 <hr>
 <form action="modifyanswer.do" method="POST">
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <c:choose> 
   <c:when test="${sessionScope.id eq 'admin'}"> 
   <button type="submit" class="btn btn-secondary btn-sm" ><span class="material-symbols-outlined">create</span>수정</button>
   </c:when> 
   </c:choose>
  </div> 
 <table class="table">
 <tbody>
  <tr> 
  <td>답변명</td>
  <td>
  <input type="hidden" name="num" value="${sessionScope.detailanswerlist['num']}" /> 
  <input class="form-control" type="text" name="title" value="${sessionScope.detailanswerlist['title']}" readonly /> 
  </td> 
  </tr>
  <tr>
  <td>답변 내용</td>
  <td>
  <textarea class="form-control" rows="15" cols="60" autofocus name="content" wrap="hard" > ${sessionScope.detailanswerlist["content"]}</textarea>
  </td> 
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
 </tbody>
</table>
</form> 
</c:when>
<c:otherwise>
  <script>
     alert("관리자만 답변을 수정할 수 있습니다.");
     history.go(-1); 
  </script>
</c:otherwise> 
</c:choose>
</div>
</div>