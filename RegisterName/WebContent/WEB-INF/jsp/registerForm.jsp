<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<form action = "/RegisterName/RegisterUser" method = "post">

<c:choose>
<c:when test="${error_registerUser==null}">
ログインID:<input type = "text" name = "id"><br>
パスワード:<input type = "password" name = "pass"><br>
名前:<input type = "text" name = "name"><br>
</c:when>
<c:otherwise>
ログインID:<input type = "text" name = "id" value = <c:out value="${error_registerUser.id}"/>><br>
パスワード:<input type = "password" name = "pass" value = <c:out value="${error_registerUser.pass}"/>><br>
名前:<input type = "text" name = "name" value = <c:out value="${error_registerUser.name}"/>><br>
</c:otherwise>
</c:choose>

<c:if test = "${errorMessage!=null}">
<c:out value="${errorMessage}"/><br>
</c:if>

<input type = "submit" value = "登録・更新">
</form>
</body>
</html>