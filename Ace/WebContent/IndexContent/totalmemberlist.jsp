<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
<div class="col-lg-6" style="padding: 10px; margin: 90px;">
<c:choose>
  <c:when test="${sessionScope.id eq 'admin'}"> <!-- 관리자 계정으로 로그인했을때  -->
    <H2 style="font-weight: bold;">전체 회원정보 관리</H2>
    <hr>
    <div style="text-align: center; ">
       <form action="deletemember.do" action="POST">
         <input type="text" class="form-control-sm" name="ID" placeholder="Input ID you want Delete." /> 
         <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
         <button class="btn btn-danger btn-sm" type="submit"><span class="material-symbols-outlined">delete</span>삭제</button>
         <button class="btn btn-secondary btn-sm" type="button" onclick="location.href='totalmemberlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
       </form>
    </div>
    <table class="table">
      <thead>
        <tr>
          <td style="font-weight: bold; ">ID</td>
          <td style="font-weight: bold; ">NAME</td>
          <td style="font-weight: bold; ">BIRTHDAY</td>
          <td style="font-weight: bold; ">JOINDATE</td>
          <td style="font-weight: bold; ">EMAIL</td> 
        </tr> 
      </thead>
      <tbody>
        <c:forEach var="MemberDTO" items="${sessionScope.totalmemberlist }" varStatus="status">
         <tr>
          <td><c:out value="${MemberDTO.id}" /></td>
          <td><c:out value="${MemberDTO.name}" /></td>
          <td><c:out value="${MemberDTO.birthday}" /></td>
          <td><c:out value="${MemberDTO.joindate}" /></td>
          <td><c:out value="${MemberDTO.email}" /></td>
         </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:when>
  <c:otherwise> 
    <p>관리자 계정으로만 전체 회원정보 관리가 가능합니다.</p> 
  </c:otherwise> 
</c:choose> 
</div> 
</div>