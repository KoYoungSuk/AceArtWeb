<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-8" style="padding: 10px; margin: 90px; ">
<c:choose> 
<c:when test="${sessionScope.id eq null}"> <!--  로그인 되어 있지 않을때  -->
<form class="signup-content" method="post" action="./signup.do">
<H2>회원 가입</H2>
<hr>
<H5>회원 정보를 입력해 주세요. 이름과 생년월일은 선택이며, 그 외 정보는 필수입니다. </H5>
<H5>이메일 주소는 중복되서는 안 됩니다. (아이디 및 비밀번호 찾기 기능에 사용됨. )</H5>
<table class="table">
   <thead>
     <tr>
       <td><label for="id" style="font-weight: bold; font-size: 20px;"><b>아이디:</b></label></td>
       <td><input type="text" class="form-control" placeholder="아이디" name="id" ></td>
     </tr>
     <tr>
       <td><label for="password" style="font-weight: bold; font-size: 20px;"><b>비밀번호:</b></label></td>
       <td><input type="password" class="form-control" placeholder="비밀번호" name="password" required></td>
     </tr>
     <tr>
      <td><label for="password" style="font-weight: bold; font-size: 20px;"><b>비밀번호 확인:</b></label></td>
      <td><input type="password" class="form-control" placeholder="비밀번호 확인" name="cpassword" required></td>
     </tr>
     <tr>
      <td><label for="email" style="font-weight: bold; font-size: 20px;"><b>이메일 주소(필수):</b></label></td>
      <td><input type="text" class="form-control" placeholder="이메일 주소" name="email" required></td> 
     </tr>
     <tr>
       <td><label for="name" style="font-weight: bold; font-size: 20px;"><b>이름(선택):</b></label></td>
       <td><input type="text" class="form-control" placeholder="이름" name="name"></td>
     </tr>
     <tr>
     <td> <label for="birthday" style="font-weight: bold; font-size: 20px;"><b> 생년월일(선택/YYYY-MM-DD): </b></label> </td>
     <td> <input type="text" class="form-control" placeholder="생년월일(YYYY-MM-DD)" name="birthday"> </td>
     </tr>
   </tbody>
</table>
<hr>
<div style="text-align: right; ">
 <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span> 뒤로가기 </button>
 <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">person_add</span> 회원가입 </button>
  &nbsp;&nbsp; 
</div>
<br> 
</form>
</c:when>
<c:otherwise> <!-- 로그인되어 있을 때  -->
 <script>
   alert("로그인한 사용자는 회원가입이 불가능합니다.");
   history.go(-1); 
 </script>
</c:otherwise>
</c:choose>
</div>
</div>