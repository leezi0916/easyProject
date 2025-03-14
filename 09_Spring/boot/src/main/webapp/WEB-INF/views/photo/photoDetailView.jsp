
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>사진게시글 상세보기</title>
  <style>
    table * {margin:5px;}
    table {width:100%;}

    #photobogi {
      text-align: center; /* 이미지 가운데 정렬 */
    }

    #photobogi img {
      width: 100%;
      max-width: 100%;
      height: auto;
      display: block;
      margin: 0 auto;
    }




  </style>
</head>
<body onload="init()">
<jsp:include page="../common/header.jsp" />

<div class="content">
  <br><br>
  <div class="innerOuter">
    <h2>사진게시글 상세보기</h2>
    <br>

    <a class="btn btn-secondary" style="float:right;" href="list.ph">목록으로</a>
    <br><br>

    <table id="contentArea" algin="center" class="table">
      <tr>
        <th width="100">제목</th>
        <td colspan="3">${p.photoTitle }</td>
      </tr>
      <tr>
        <th>작성자</th>
        <td>${p.photoWriter }</td>
        <th>업로드 날짜</th>
        <td>${p.createDate }</td>
      </tr>
      <tr id="photobogi">
        <td  colspan="3">
          <c:choose>
            <c:when test="${not empty p.changeName}">
              <img src="${pageContext.request.contextPath}${p.changeName}" alt="업로드된 이미지">
            </c:when>
            <c:otherwise>
              <img src="/resources/default.png" alt="기본 이미지">
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </table>
    <br>


    <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
    <div align="center">
      <c:if test="${loginUser.userId eq p.photoWriter}">
        <a class="btn btn-primary" onclick="postFormSubmit('edit')">수정하기</a>
        <a class="btn btn-danger" onclick="postFormSubmit('delete')">삭제하기</a>
      </c:if>
    </div>
    <br><br>

    <form action="" method="GET" id="postForm">
      <input type="hidden" name="pno" value="${p.photoNo}">
    </form>

    <script>
      function postFormSubmit(type){
        const formEl = document.querySelector("#postForm");
        switch(type){
          case "edit" : {
            //formEl.action = "updateForm.ph";
            $(formEl).attr("action", "updateForm.ph");
          }break;
          case "delete":{
            //formEl.action = "delete.ph";
            $(formEl).attr("action", "delete.ph")
          }break;
        }

        $(formEl).submit();
      }
    </script>

    <table id="likeArea" class="table" align="center">
      <thead>
      <c:choose>
        <c:when test="${!empty loginUser }">
          <tr>
            <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addLike();">좋아요</button></th>
          </tr>
        </c:when>
      </c:choose>

      <tr>
        <td colspan="3">👍(<span id="lcount">0</span>)</td>
      </tr>

      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
  <br><br>
  <script>
    function addLike(){
      const likeCount = document.querySelector('#lcount');

    }
  </script>



</div>

<jsp:include page="../common/footer.jsp" />
</body>
</html>
