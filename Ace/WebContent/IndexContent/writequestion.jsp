<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px">
   <c:choose>
     <c:when test="${sessionScope.id ne null}">
       <H2 style="font-weight: bold; ">질문하기</H2>
       <hr> 
       <form action="writequestion.do" method="POST" > 
       <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" onclick="history.go(-1);" type="button"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>작성</button>&nbsp;&nbsp; 
       </div>
       <table class="table">
        <tr>
          <td><label for="title">질문명</label></td>
          <td><input type="text" class="form-control" name="title"></td> 
        </tr> 
        <tr>
         <td><label for="content">질문 내용</label></td>
         <td><textarea class="form-control" rows="15" cols="60" autofocus name="content" wrap="hard" ></textarea></td>
        </tr>
        <tr>
         <td><label for="access">접근모드</label></td>
         <td style="text-align: right;">
           <input type="checkbox" class="custom-control-input" name="access" id="jb-check-1" value="secret">
           <label class="custom-control-label" for="jb-check-1">비밀 모드(관리자 및 작성자만 볼수 있음)</label>
        </td>
        </tr>
       </table>
       </form> 
     </c:when>
     <c:otherwise>
      <p>질문하기 기능은 로그인을 해야 사용가능합니다.</p> 
     </c:otherwise> 
     </c:choose>
 </div> 
</div>