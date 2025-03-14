<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>사진 게시글 수정하기!</title>
<style>
 #updateForm>table {width:100%;}
 #updateForm>table * {margin:5px;}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>사진 게시글 수정하기</h2>
            <br>

            <form id="updateForm" method="post" action="update.ph" enctype="multipart/form-data">
            	<input type="hidden" name="photoNo" value="${p.photoNo }">
                <table algin="center">
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td><input type="text" id="title" class="form-control" value="${p.photoTitle }" name="photoTitle" required></td>
                    </tr>
                    <tr>
                        <th><label for="writer">작성자</label></th>
                        <td><input type="text" id="writer" class="form-control" value="${p.photoWriter }" name="photoWriter" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="upfile">사진파일</label></th>
                        <td>
                            <input type="file" id="upfile" class="form-control-file border" name="reupfile">
                            
                            <c:if test="${not empty p.originName}">
								현재 업로드된 파일 : 
	                            <a href="${p.changeName }" download="${p.originName }">${p.originName }</a>
                       			<input type="hidden" name="originName" value="${p.originName }">
                       			<input type="hidden" name="changeName" value="${p.changeName }">
                       		</c:if>
                        </td>
                    </tr>

                </table>
                <br>

                <div align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="reset" class="btn btn-danger">이전으로</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>
    
    <jsp:include page="../common/footer.jsp" />

</body>
</html>