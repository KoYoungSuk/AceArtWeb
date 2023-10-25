<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px">
   <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
       <H2 style="font-weight: bold; ">자료실 수정</H2>
       <hr> 
       <form action="modifyboardlist.do" method="POST" enctype="multipart/form-data">
       <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" onclick="history.go(-1);" type="button"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>수정</button>&nbsp;&nbsp; 
       </div>
       <table class="table">
        <tr>
          <td><label for="title">제목</label></td>
          <td><input type="hidden" name="num" value="${sessionScope.detailboardlist['num']}" /> 
              <input type="text" class="form-control" name="title" value="${sessionScope.detailboardlist['title']}"></td> 
        </tr> 
        <tr>
         <td><label for="content">내용</label></td>
         <td><textarea class="form-control" rows="20" cols="100" autofocus name="content" wrap="hard" >${sessionScope.detailboardlist['content']}</textarea></td>
        </tr>
        <!-- 
        <tr>
         <td><label for="access">권한 설정</label></td>
         <td style="text-align: center;">
           <input type="radio" class="custom-control-input" name="access" id="jb-radio-1" value="admin" checked>
           <label class="custom-control-label" for="jb-radio-1">Administrator Mode</label>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="radio" class="custom-control-input" name="access" id="jb-radio-2" value="member">
           <label class="custom-control-label" for="jb-radio-2">Member Mode</label>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="radio" class="custom-control-input" name="access" id="jb-radio-3" value="anonymous">
           <label class="custom-control-label" for="jb-radio-3">NonMember Mode</label>
        </td>
        </tr>
        --> 
        <tr>
         <td><label for="files">파일 업로드</label></td>
         <td><input type="file" name="file">${sessionScope.detailboardlist['files']}</td> 
        </tr> 
        <tr>
         <td><label for="savedate">작성 날짜</label></td>
         <td>${sessionScope.detailboardlist['savedate']}</td> 
        </tr>
        <tr>
         <td><label for="modifydate">수정 날짜</label></td>
         <td>${sessionScope.detailboardlist['modifydate']}</td> 
        </tr> 
       </table>
       </form> 
     </c:when> 
     <c:otherwise>
       <p>관리자 계정으로만 자료실 수정이 가능합니다.</p> 
     </c:otherwise> 
   </c:choose> 
 </div> 
</div>