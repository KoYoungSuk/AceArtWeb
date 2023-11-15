<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
 <H2 style="font-weight: bold;">질문 수정</H2>
 <hr>
 <form action="modifyquestion.do" method="POST">
 <div style="text-align: right;">
   <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
   <button type="submit" class="btn btn-secondary btn-sm"><span class="material-symbols-outlined">create</span>수정</button>
 </div> 
 <table class="table">
 <tbody>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">질문명</td>
  <td>
  <input type="hidden" name="num" value="${sessionScope.detailquestionlist['num']}" />
  <input type="hidden" name="user" value="${sessionScope.detailquestionlist['user']}" /> 
  <input type="text" class="form-control"  name="title" value="${sessionScope.detailquestionlist['title']}" /> 
  </td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">질문 내용</td>
  <td>
  <textarea class="form-control" rows="15" cols="60" autofocus name="content" wrap="hard" >
  ${sessionScope.detailquestionlist["content"]}
  </textarea>
  </td> 
  </tr>
  <tr>
  <td style="font-weight: bold; font-size: 20px;">접근 모드</td>
  <td>
  <div style="text-align: right;"> 
  <c:choose>
  <c:when test="${sessionScope.detailquestionlist['access'] eq 'secret'}">
    <input type="checkbox" class="custom-control-input" name="access" id="jb-check-1" value="secret" checked>
    <label class="custom-control-label" for="jb-check-1">비밀 모드(관리자 및 작성자만 볼수 있음)</label>
  </c:when>
  <c:otherwise>
    <input type="checkbox" class="custom-control-input" name="access" id="jb-check-1" value="secret">
    <label class="custom-control-label" for="jb-check-1">비밀 모드(관리자 및 작성자만 볼수 있음)</label>
  </c:otherwise>
  </c:choose> 
  </div>
  </td> 
  </tr>
 </tbody>
</table>
</form>
</div>
</div>