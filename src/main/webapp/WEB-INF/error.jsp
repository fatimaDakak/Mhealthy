<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <c:if test="${not empty errorMessage}">
    <div class="mt-3">
        <p>${errorMessage}</p>
    </div>
       </c:if></body>
</html>