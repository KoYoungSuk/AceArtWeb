<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="col-lg-8" style="padding: 10px; margin: 90px; ">
   <c:choose> 
   <c:when test="${sessionScope.id ne null}"> <!--  로그인되어 있을 때  -->
    <H2 style="font-weight: bold; ">회원 정보 목록</H2>
    <hr> 
    <table class="table">
         <tr>
            <td style="font-weight: bold; font-size: 20px;">아이디:</td>
            <td>${sessionScope.id}</td>
         </tr>
         <tr>
            <td style="font-weight: bold; font-size: 20px;">비밀번호 변경</td>
            <td><button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=14'"><span class="material-symbols-outlined">key</span>비밀번호 수정</button> 
         </tr>
         <tr>
            <td style="font-weight: bold; font-size: 20px;">이름:</td>
            <td>${sessionScope.name}</td>
         </tr>
         <tr>
            <td style="font-weight: bold; font-size: 20px;">생년월일:</td>
            <td>${sessionScope.birthday}</td>
         </tr>   
         <tr>
           <td style="font-weight: bold; font-size: 20px;">이메일 주소:</td>
           <td>${sessionScope.email}</td>
         </tr>
         <tr>
            <td style="font-weight: bold; font-size: 20px;">가입날짜:</td>
            <td>${sessionScope.joindate}</td>
         </tr>         
    </table>
    <hr>
    <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
    <button class="btn btn-secondary btn-sm" onclick="location.href='modifymember.do'"><span class="material-symbols-outlined">create</span>회원정보 수정</button>            
    <button class="btn btn-danger btn-sm"onclick="location.href='deletemember.do'"><span class="material-symbols-outlined">delete</span>회원정보 삭제</button>
    </c:when>
    <c:otherwise> <!--  로그인하지 않았을 때  -->
     <script>
       alert("회원정보를 확인하려면 먼저 로그인해 주세요.");
       history.go(-1); 
     </script>
    </c:otherwise> 
    </c:choose>
</div>