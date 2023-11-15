<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <c:choose>
 <c:when test="${sessionScope.id eq 'admin'}">
  <H2 style="font-weight: bold;">공지사항 삭제 </H2>
  <hr>
  <H5>경고: 공지사항 정보를 삭제하면 다시 복구할 수 없습니다. </H5>
  <form action="deletenoticeboard.do" method="POST">
  <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <button type="submit" class="btn btn-danger btn-sm" ><span class="material-symbols-outlined">delete</span>삭제</button> 
  </div> 
 <table class="table">
 <tbody>
  <tr>
   <td style="font-weight: bold; font-size: 20px;">글 번호</td>
   <td>
     ${sessionScope.detailnoticelist["num"]}
     <input type="hidden" name="num" value= "${sessionScope.detailnoticelist['num']}" /> 
   </td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">제목</td>
  <td>${sessionScope.detailnoticelist["title"]}</td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">내용</td>
  <td>${sessionScope.detailnoticelist["content"]}</td> 
  </tr>
 <tr>
  <td style="font-weight: bold; font-size: 20px;">작성 날짜</td>
  <td>${sessionScope.detailnoticelist["savedate"]}</td>
 </tr> 
 <tr>
  <td style="font-weight: bold; font-size: 20px;">수정 날짜</td>
  <td>${sessionScope.detailnoticelist["modifydate"]}</td> 
 </tr> 
 </tbody>
</table>
</form>
</c:when>
<c:otherwise>
<script>
alert("공지사항 삭제는 관리자 계정으로만 가능합니다.");
history.go(-1); 
</script>
</c:otherwise>
</c:choose>
</div>
</div>