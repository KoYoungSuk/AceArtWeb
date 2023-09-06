<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="col-lg-6" style="padding: 10px; margin: 90px; ">
    <H1>회원 정보 목록</H1>
    <hr> 
    <table class="table">
         <tr>
            <td>아이디:</td>
            <td>${sessionScope.id}</td>
         </tr>
         <tr>
            <td>이름:</td>
            <td>${sessionScope.name}</td>
         </tr>
         <tr>
            <td>생년월일:</td>
            <td>${sessionScope.birthday}</td>
         </tr>   
         <tr>
            <td>가입날짜:</td>
            <td>${sessionScope.joindate}</td>
         </tr>        
    </table>
    <hr>
    <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
    <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=10'"><span class="material-symbols-outlined">create</span>회원정보 수정</button>            
    <button class="btn btn-danger btn-sm"onclick="location.href='index.jsp?page=11'"><span class="material-symbols-outlined">delete</span>회원정보 삭제</button>
</div>