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
<p>下記のユーザーを登録します</p>
<p>
ログインID:<c:out value="${registerUser.id}"/><br>
名前:<c:out value="${registerUser.name}"/><br>

</p>
<a href = "/RegisterName/RegisterUser?action=done">登録</a>
<a href = "/RegisterName/RegisterUser">戻る</a>
</body>
</html>