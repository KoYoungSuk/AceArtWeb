<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:choose>
  <c:when test="${param.page_count eq null}">
    <c:set var="pagecount" value="1" /> 
  </c:when>
  <c:otherwise>
   <c:set var="pagecount" value="${param.page_count}" /> 
  </c:otherwise> 
</c:choose> 
<div class="row">
 <div class="col-lg-8" style="padding: 10px; margin: 90px;">
   <h2 style="font-weight: bold;">Question & Answer </h2>
   <hr>
   <div style="text-align: right; ">
      <button class="btn btn-secondary btn-sm" onclick="history.go(-1);"><span class="material-symbols-outlined">arrow_back_ios</span>뒤로가기</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href='index.jsp?page=34'"><span class="material-symbols-outlined">create</span>질문하기</button>
      <button class="btn btn-secondary btn-sm" onclick="location.href='totalquestionlist.do'"><span class="material-symbols-outlined">refresh</span>새로고침</button>
   </div>
  <div style="text-align: center;">
          <H4 style="font-weight: bold;">
          <c:choose>
          <c:when test="${pagecount ne 1}"> <!-- 첫번째 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalquestionlist.do?page_count=${pagecount - 1}'"><span class="material-symbols-outlined">arrow_back_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise>
          </c:choose>
          &nbsp;&nbsp;&nbsp;&nbsp;
          CURRENT PAGE : PAGE ${pagecount} 
          &nbsp;&nbsp;&nbsp;&nbsp;
          <c:choose> 
          <c:when test="${pagecount ne sessionScope.pagenum_question}"> <!-- 마지막 페이지가 아닐때 -->
          <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='totalquestionlist.do?page_count=${pagecount + 1}'"><span class="material-symbols-outlined">arrow_forward_ios</span></button>
          </c:when>
          <c:otherwise></c:otherwise> 
          </c:choose>
          </H4> 
  </div> 
   <table class="table">
     <thead>
     <tr>
      <th></th>
      <th></th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성날짜</th>
      <th>수정날짜</th> 
     </tr>
     </thead> 
     <tbody>
     <c:forEach var="QuestionDTO" items="${sessionScope.totalquestionlist}" begin="${beginnumber_question}" end="${endnumber_question}">
      <tr>
      <td><c:out value="${QuestionDTO.num}" /> </td>
      <td>
      <c:choose> 
      <c:when test="${QuestionDTO.access eq 'secret'}">
         <span class="material-symbols-outlined">lock</span>
      </c:when>
      <c:otherwise></c:otherwise>
      </c:choose>
      </td>
      <td><a href="detailquestion.do?num=${QuestionDTO.num}"><c:out value="${QuestionDTO.title}" /></a></td>
      <td><c:out value="${QuestionDTO.user}" /></td>
      <td><c:out value="${QuestionDTO.savedate}" /></td>
      <td><c:out value="${QuestionDTO.modifydate}" /></td>
      <td>
      <!-- <a href="deletequestion.do?num=${QuestionDTO.title}">삭제</a> --> 
      <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='deletequestion.do?num=${QuestionDTO.num}'"><span class="material-symbols-outlined">delete</span></button>
      </td>
      </tr>
      <tr>
      <td></td>
      <td></td>
      <td><a href="detailanswer.do?num=${QuestionDTO.num2}">${QuestionDTO.title2}</a></td> 
      <td>${QuestionDTO.user2}</td> 
      <td>${QuestionDTO.savedate2}</td>
      <td>${QuestionDTO.modifydate2}</td> 
      </tr> 
     </c:forEach>
     </tbody> 
   </table>
   <hr>
   <div style="text-align: center;">
    <c:forEach var="num" begin="1" end="${sessionScope.pagenum_question}">
      <c:choose>
      <c:when test="${pagecount == num}">
      <button type="button" class="btn btn-danger" onclick="location.href='totalquestionlist.do?page_count=${num}'">${num}</button>
      </c:when>
      <c:otherwise>
      <button type="button" class="btn btn-secondary" onclick="location.href='totalquestionlist.do?page_count=${num}'">${num}</button>
      </c:otherwise>
      </c:choose>
    </c:forEach>
   </div>
 </div> 
</div>