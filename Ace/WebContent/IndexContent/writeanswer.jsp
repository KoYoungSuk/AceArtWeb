<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px">
   <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
       <H2 style="font-weight: bold; ">답변하기</H2>
       <hr> 
       <form action="writeanswer.do" method="POST" > 
       <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" onclick="history.go(-1);" type="button"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>답변하기</button>&nbsp;&nbsp; 
       </div>
       <table class="table">
        <tr>
          <td><label for="title">답변 제목 </label></td>
          <td>
          <input type="text" class="form-control" name="title" value="RE:  ${sessionScope.detailquestionlist['title']}" readonly />
          <input type="hidden" name="access" value="${sessionScope.detailquestionlist['access']}" /> 
          <input type="hidden" name="qnum" value="${sessionScope.detailquestionlist['num']}" /> 
          </td> 
        </tr> 
        <tr>
         <td><label for="content">답변 내용</label></td>
         <td><textarea class="form-control" rows="15" cols="60" autofocus name="content" wrap="hard" ></textarea></td>
        </tr>
       </table>
       </form> 
     </c:when>
     <c:otherwise>
      <script>
        alert("관리자만 답변이 가능합니다.");
        history.go(-1); 
      </script>
     </c:otherwise> 
     </c:choose>
 </div> 
</div>