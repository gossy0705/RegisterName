<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー更新</title>
</head>
<body>
<p>下記のユーザーを更新します</p>
<p>
ログインID:<c:out value="${registerUser.id}"/><br>
名前(更新後):<c:out value="${registerUser.name}"/><br>
</p>
<a href = "/RegisterName/RegisterUser?action=update">更新</a>
<a href = "/RegisterName/RegisterUser">戻る</a>
</body>
</html>