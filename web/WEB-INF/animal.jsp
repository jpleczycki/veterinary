<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>

<h1>Zwierzak: ${animal}</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th> width="500">Description<</th>
        <th>Data</th>
    </tr>
    <tr>
        <td>{$animal.id}</td>
        <td>{$animal.name}</td>
        <td>{$animal.description}</td>
    </tr>
    <c:forEach var="notification" items="{animal.notifications}">
    <tr>
        <td>
        <td>{notification.animal.name}</td>
        </td>
    </tr>
    </c:forEach>
<%--</table>--%>
<%--<form method="get">--%>
    <%--<input type="hidden" name="id" value="${animal.id}">--%>
<%--</form>--%>


<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>