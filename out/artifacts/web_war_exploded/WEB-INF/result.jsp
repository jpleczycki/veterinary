<%@page import="pl.jacek.veterinary.model.Animal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% Animal animal = (Animal)request.getAttribute("animal"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Wynik zapytania <%= request.getAttribute("option") %></h1>
<p>W wyniku Twojego zapytania otrzymano następujacy wynik:</p>
<p>Tytuł: <%= animal.getName() %><br>
    ISBN: <%= animal.getUrl() %><br>
    Opis: <%= animal.getDescription() %></p>
</body>
</html>