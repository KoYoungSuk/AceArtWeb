<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
  <div class="col-lg-8" style="padding: 10px; margin: 90px;">
    <h2 style="font-weight: bold;">작품 상세정보</h2>
    <hr>
    <div style="text-align: right;">
     <button type="button" class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
     <c:choose> 
     <c:when test="${sessionScope.id eq 'admin'}">
     <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='modifyworks.do?num=${sessionScope.detailworkslist['num']}'"><span class="material-symbols-outlined">create</span>수정</button>
     <button type="button" class="btn btn-danger btn-sm" onclick="location.href='deleteworks.do?num=${sessionScope.detailworkslist['num']}'"><span class="material-symbols-outlined">delete</span>삭제</button>
     </c:when>
     <c:otherwise></c:otherwise>
     </c:choose>
    </div>
    <table class="table">
      <tr>
        <td><label for="name" style="font-weight: bold;  font-size: 20px;">이름</label></td>
        <td>${sessionScope.detailworkslist['name']}</td> 
      </tr>
      <tr>
       <td><label for="pictures" style="font-weight: bold;  font-size: 20px; ">사진</label></td>
       <td><img src="/Pictures/${sessionScope.detailworkslist['num']}/${sessionScope.detailworkslist['picture']}" width="500" height="350" /></td>
      </tr>
      <tr>
       <td><label for="description" style="font-weight: bold; font-size: 20px;">설명 </label></td>
       <td>${sessionScope.detailworkslist['description']}</td>
      </tr>
      <tr>
       <td><label for="installdate" style="font-weight: bold; font-size: 20px;">설치 날짜</label></td>
       <td>${sessionScope.detailworkslist['installdate']}</td>
      </tr>
      <tr>
       <td><label for="savedate" style="font-weight: bold;  font-size: 20px;">작성 날짜</label></td>
       <td>${sessionScope.detailworkslist['savedate']}</td>
      </tr>
      <tr>
       <td><label for="modifydate" style="font-weight: bold;  font-size: 20px;">수정 날짜</label></td>
       <td>${sessionScope.detailworkslist['modifydate']}</td>
      </tr> 
    </table> 
  </div>
</div>