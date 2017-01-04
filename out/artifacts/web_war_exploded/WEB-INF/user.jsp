<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Viewer</title>
</head>
<body>
<h1>Twoje konto</h1>

<h2>    <br><small>ID użytkownika: </small>${sessionScope.user.id}
    <br><small>Nazwa użtykownika: </small>${sessionScope.user.username}
    <br><small>Adres email: </small>${sessionScope.user.email}</h2>
</body>
</html>