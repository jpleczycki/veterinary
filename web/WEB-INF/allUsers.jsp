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

<jsp:include page="fragment/navbar.jspf" />

<c:if test="${not empty requestScope.users}">
    <c:forEach var="user" items="${requestScope.users}">
        <div class="container">
            <div class="row bs-callout bs-callout-primary">
                <div class="col col-md-11 col-sm-10">
                    <h3 class="centered">
                        <a href="<c:out value="${user.url}" />">
                            <c:out value="${user.id}." />
                            <c:out value="${user.username}" /></a></h3>
                    <c:url var="url" scope="page" value="user">
                        <c:param name="user.id" value="user.id" />
                    </c:url>

                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>