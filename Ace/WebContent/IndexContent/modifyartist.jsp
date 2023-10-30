<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
   <div class="col-lg-8" style="padding: 10px; margin: 90px;">
     <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
     <H2 style="font-weight: bold;">작가 정보 수정</H2>
     <hr>
     <form action="modifyartist.do" method="POST" enctype="multipart/form-data">
     <div style="text-align: right;">
        <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
        <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>수정</button>
     </div>  
     <table class="table">
       <tr>
         <td><label for="name">작가 이름</label></td>
         <td><input type="text" class="form-control mr-sm-10" name="name" value="${sessionScope.artistlist['name']}" />
             <input type="hidden" name="num" value="${sessionScope.artistlist['num']}" /> 
         </td> 
       </tr>
       <tr>
         <td><label for="career">작가 학력&경력</label></td>
         <td><textarea class="form-control mr-sm-10" rows="20" cols="100" autofocus name="career" wrap="hard" >${sessionScope.artistlist['career']}</textarea></td> 
       </tr>
       <tr>
        <td>작품1 추가</td>
        <td>
          <input type="file" name="uploadFile01" /> 
                    현재 파일명: ${sessionScope.artistlist['work1']}
          <input type="hidden" name="oldFile1" value="${sessionScope.artistlist['work1']}" /> 
        </td> 
       </tr>
       <tr>
        <td>작품2 추가</td>
        <td>
         <input type="file" name="uploadFile02" />
                   현재 파일명: ${sessionScope.artistlist['work2']}
         <input type="hidden" name="oldFile2" value="${sessionScope.artistlist['work2']}" /> 
        </td> 
       </tr> 
     </table>
     </form> 
     </c:when>
     <c:otherwise>
      <p>관리자만 작가 정보 수정이 가능합니다.</p> 
     </c:otherwise> 
     </c:choose> 
   </div> 
</div>