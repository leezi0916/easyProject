<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 0 auto;
        }
        .image-container {
            width: 100%;
            padding-top: 100%; /* 정사각형 유지 (비율 1:1) */
            position: relative;
            margin-bottom: 40px;
        }

        .image-container img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover; /* 이미지가 찌그러지지 않으면서 꽉 차게 설정 */
        }

    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="content">
    <br><br>
    <div class="innerOuter" style="padding:5% 10%;">
        <h2>사진게시판</h2>
        <br>
        <!-- 로그인 후 상태일 경우만 보여지는 사진게시하기 버튼 -->
        <c:if test="${not empty loginUser}">
            <a class="btn btn-secondary" style="float:right;" href="enrollForm.ph">사진게시하기</a>
            <br>
        </c:if>
        <br>
        <table id="photoList" class="table table-hover" align="center">
            <thead>
            <tr>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>업로드 날짜</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${list}">
                <tr onclick = "location.href = 'detail.ph?pno=${p.photoNo}'">
                    <td>${p.photoNo}</td>
                    <td>${p.photoWriter}</td>
                    <td>${p.photoTitle}</td>
                    <td>${p.count}</td>
                    <td>${p.createDate}</td>
                </tr>
                <tr onclick = "location.href = 'detail.ph?pno=${p.photoNo}'">
                    <td colspan="5" >
                        <div class="image-container">
                            <c:choose>
                                <c:when test="${not empty p.changeName}">
                                    <img src="${pageContext.request.contextPath}${p.changeName}" alt="업로드된 이미지">
                                </c:when>
                                <c:otherwise>
                                    <img src="/resources/default.png" alt="기본 이미지">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <br>

        <div id="pagingArea">
            <ul class="pagination">

                <c:choose>
                    <c:when test="${ pi.currentPage eq 1 }">
                        <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="list.ph?cpage=${pi.currentPage - 1}">Previous</a></li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                    <li class="page-item"><a class="page-link" href="list.ph?cpage=${p}">${p}</a></li>
                </c:forEach>

                <c:choose>
                    <c:when test="${ pi.currentPage eq pi.maxPage }">
                        <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="list.ph?cpage=${pi.currentPage + 1}">Next</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>

        <br clear="both"><br>
        <br><br>
    </div>
    <br><br>
    </div>
<jsp:include page="../common/footer.jsp" />
</body>
</html>
