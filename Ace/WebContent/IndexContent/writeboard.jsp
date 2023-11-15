<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px">
   <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
       <H2 style="font-weight: bold; ">자료실 등록</H2>
       <hr> 
       <form action="writeboard.do" method="POST" enctype="multipart/form-data">
       <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" onclick="history.go(-1);" type="button"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>작성</button>&nbsp;&nbsp; 
       </div>
       <table class="table">
        <tr>
          <td style="font-weight: bold; font-size: 20px;"><label for="title">제목</label></td>
          <td><input type="text" class="form-control" name="title"></td> 
        </tr> 
        <tr>
         <td style="font-weight: bold; font-size: 20px;"><label for="content">내용</label></td>
         <td><textarea class="form-control" rows="20" cols="100" autofocus name="content" wrap="hard" ></textarea></td>
        </tr>
        <tr>
         <td style="font-weight: bold; font-size: 20px;"><label for="files">파일 업로드</label></td>
         <td><input type="file" name="file"></td> 
        </tr> 
       </table>
       </form> 
     </c:when> 
     <c:otherwise>
     <script>
       alert("관리자 계정으로만 자료실 등록이 가능합니다.");
       history.go(-1); 
     </script>
     </c:otherwise> 
   </c:choose> 
 </div> 
</div>