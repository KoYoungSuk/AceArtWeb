<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<meta charset="EUC-KR">
<title>PDF Upload</title>
</head>
<body>
  <c:choose> 
  <c:when test="${sessionScope.id eq 'admin'}">
  <H1>PDF Upload </H1>
  <hr>
  <form action="pdfupload.do" method="POST" enctype="multipart/form-data">
    <input type="file" name="file"> 
    <hr> 
    <button type="submit">Upload</button>
    <button type="button" onclick="history.go(-1);">뒤로가기</button> 
  </form>
  </c:when>
  <c:otherwise>
    <script>
      history.go(-1); 
    </script>
  </c:otherwise> 
  </c:choose>
</body>
</html>