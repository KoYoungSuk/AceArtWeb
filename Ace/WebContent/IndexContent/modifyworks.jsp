<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
  <div class="col-lg-8" style="padding: 10px; margin: 90px;">
   <c:choose>
     <c:when test="${sessionScope.id eq 'admin'}">
       <H2 style="font-weight: bold;">작품 수정</H2>
       <hr>
       <form action="modifyworks.do" method="POST" enctype="multipart/form-data">
        <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>수정</button>
        </div>
        <table class="table">
          <tr>
           <td>작품명</td>
           <td>
             <input type="text" class="form-control mr-sm-10" name="name" value='${sessionScope.detailworkslist["name"]}' />
             <input type="hidden" name="num" value="${sessionScope.detailworkslist['num']}" /> 
           </td> 
          </tr>
          <tr>
           <td>작품설명</td>
           <td><textarea class="form-control mr-sm-10" rows="20" cols="100" autofocus name="description" wrap="hard" >${sessionScope.detailworkslist["description"]}</textarea></td> 
          </tr>
          <tr>
           <td>설치날짜</td>
           <td><input type="text" class="form-control mr-sm-10" name="installdate" value="${sessionScope.detailworkslist['installdate']}" /></td> 
          </tr>
          <tr>
            <td>사진파일 업로드</td>
            <td><input type="file" name="file" />현재 업로드된 사진파일: ${sessionScope.detailworkslist['picture']}</td> 
          </tr> 
        </table>
       </form>
     </c:when>
     <c:otherwise>
      <p>관리자만 작품 정보 수정이 가능합니다.</p> 
     </c:otherwise> 
   </c:choose> 
  </div>
</div>