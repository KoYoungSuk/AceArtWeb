<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px; ">
  <c:choose>
    <c:when test="${sessionScope.id eq null}"> <!--  로그인 되어 있지 않을때  -->
     <H2 style="weight: bold">비밀번호 찾기</H2>
     <hr>
     <H5>아이디와 이메일 주소를 입력해 주세요. 입력한 이메일 주소로 임시 비밀번호를 보내 드리겠습니다. </H5>
     <H5>임시 비밀번호로 로그인한 다음에는 반드시 비밀번호를 변경해 주세요. </H5>
     <form action="findpw.do" method="POST"> 
     <table class="table">
      <tbody>
       <tr>
         <td><label for="id">아이디: </label></td>
         <td><input type="text" class="form-control" name="id" /></td>
       </tr>
       <tr>
        <td><label for="email">이메일 주소: </label></td>
        <td><input type="text" class="form-control" name="email" /></td> 
       </tr>
      </tbody> 
     </table>
     <div style="text-align: right;">
          <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>&nbsp;&nbsp; 
          <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">search</span>Find&amp;비밀번호 초기화</button> 
    </div> 
    </form> 
    </c:when>
    <c:otherwise> <!--  로그인되어 있을 때  -->
     <p>로그인한 사용자는 비밀번호 찾기 기능을 이용할 수 없습니다.</p> 
    </c:otherwise>
  </c:choose>
</div> 
</div>