<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6" style="padding: 10px; margin: 90px; ">
<form class="signup-content" method="post" action="./checksignup.do">
<H2>회원 가입</H2>
<hr>
<table class="table">
   <thead>
     <tr>
       <td><label for="id"><b>아이디:</b></label></td>
       <td><input type="text" class="form-control" placeholder="아이디" name="id" ></td>
     </tr>
     <tr>
       <td><label for="password"><b>비밀번호:</b></label></td>
       <td><input type="password" class="form-control" placeholder="비밀번호" name="password" required></td>
     </tr>
     <tr>
      <td><label for="password"><b>비밀번호 확인:</b></label></td>
      <td><input type="password" class="form-control" placeholder="비밀번호 확인" name="cpassword" required></td>
     </tr>
     <tr>
       <td><label for="name"><b>이름(선택):</b></label></td>
       <td><input type="text" class="form-control" placeholder="이름" name="name"></td>
     </tr>
     <tr>
     <td> <label for="birthday"><b> 생년월일(선택/YYYY-MM-DD): </b></label> </td>
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
</div>