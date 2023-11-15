<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px;">
  <c:choose>
    <c:when test="${sessionScope.id eq 'admin'}">
      <H2 style="font-weight: bold;">작가 정보 추가</H2>
      <hr>
      <form action="writeartist.do" method="POST" enctype="multipart/form-data">
        <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">create</span>추가</button>
        </div> 
        <table class="table">
          <tr>
            <td><label for="name" style="font-weight: bold; font-size: 20px;">작가 이름</label></td>
            <td><input type="text" class="form-control mr-sm-10" name="name" /></td> 
          </tr>
          <tr>
           <td><label for="career" style="font-weight: bold; font-size: 20px;">작가 경력</label></td>
           <td><textarea class="form-control mr-sm-10" rows="20" cols="100" autofocus name="career" wrap="hard" ></textarea></td> 
          </tr>
          <tr>
           <td><label for="work1" style="font-weight: bold; font-size: 20px;">작품1 업로드 </label></td>
           <td><input type="file" name="uploadFile01" /> </td> 
          </tr>
          <tr>
           <td><label for="work2" style="font-weight: bold; font-size: 20px;">작품2 업로드 </label></td>
           <td><input type="file" name="uploadFile02" /></td> 
          </tr> 
        </table>
      </form>
    </c:when>
    <c:otherwise>
    <script>
          alert("관리자만 작가 정보 추가가 가능합니다.");
          history.go(-1); 
    </script>
    </c:otherwise> 
  </c:choose>
</div> 
</div> 