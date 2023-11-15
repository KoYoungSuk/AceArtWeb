<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
 <div class="col-lg-6" style="margin: 90px; padding: 10px;">
   <c:choose>
     <c:when test="${sessionScope.id eq null}"> <!-- 로그인되어 있지 않을 때  -->
       <H2 style="font-weight: bold">아이디 찾기 </H2>
       <hr>
       <H5>가입할때 입력했던 이메일 주소를 입력해 주세요. 이 이메일 주소로 아이디를 보내드릴 예정입니다. </H5> 
       <form action="findid.do" method="POST"> 
              이메일 주소: <input type="text" class="form-control" name="email" />
       <br> 
       <div style="text-align: right;"> 
       <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>&nbsp;&nbsp; 
       <button class="btn btn-secondary btn-sm" type="submit"><span class="material-symbols-outlined">search</span>찾기</button>
       </div>
       </form>
     </c:when>
     <c:otherwise> <!-- 로그인되어 있을 때  -->
       <script>
         alert("로그인한 사용자는 아이디 찾기 기능을 사용할 수 없습니다.");
         history.go(-1); 
       </script>
     </c:otherwise>
   </c:choose> 
 </div>
</div>