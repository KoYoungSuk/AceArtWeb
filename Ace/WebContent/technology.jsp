<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="./BS/bootstrap.min.css" />
<link rel="stylesheet" href="./BS/bootstrap.css" />
<!--  Span Icon By Google -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<title>사용기술 안내 </title>
<style>
    .htitle
    {
         background-color: blue; 
         text-align: center;
         color: white;
         border: ridge; 
    }
     body
    {
        background-color: #008080; 
    }
</style>
</head>
<body>
 <div class="container-fluid">
   <div class="row">
     <div class= "col-sm-8" style="padding: 0px; margin: 70px; background-color: #DCDCDC; ">
       <h3 class="htitle">사용기술 안내  </h3>
       <hr>
       &nbsp;<p>Front-End(Browser-Side): HTML5, CSS, Javascript (Bootstrap 4.4/JQuery), JSTL </p> 
       &nbsp;<p>Back-End(Server-Side): Java Servlet </p>
       &nbsp;<p>Server Environment(Development): Apache Tomcat 9.0/Windows 11 </p>
       &nbsp;<p>Server Environment(Real): Apache Tomcat 9.0/ </p>
       &nbsp;<p><a href='#'>GitHub 주소</a></p> 
       &nbsp;<p>이 사이트는 구글 크롬 및 모질라 파이어폭스, 애플 사파리 브라우저에 최적화되어 있습니다.  </p> 
       <hr> 
       <div style="text-align: center">
         <button class="btn btn-secondary btn-sm" type="button" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span> 뒤로가기 </button>
         <p></p> 
       </div>
     </div> 
   </div>
 </div>
</body>
</html>