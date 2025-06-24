<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>View Student</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h1>Student Details</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/students">Back to List</a>
        <a href="${pageContext.request.contextPath}/">Home</a>
    </nav>

    <c:choose>
        <c:when test="${not empty error}">
            <p class="error"><c:out value="${error}" /></p>
        </c:when>
        <c:otherwise>
            <p><strong>ID:</strong> <c:out value="${student.id}" /></p>
            <p><strong>Name:</strong> <c:out value="${student.name}" /></p>
            <p><strong>Email:</strong> <c:out value="${student.email}" /></p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>