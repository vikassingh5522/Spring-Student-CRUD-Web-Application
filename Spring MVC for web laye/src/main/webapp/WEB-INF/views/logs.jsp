<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>View Logs</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
  <h1>Application Logs</h1>
  <nav>
    <a href="${pageContext.request.contextPath}/students">Back to List</a>
    <a href="${pageContext.request.contextPath}/">Home</a>
  </nav>
  <c:if test="${not empty error}">
    <p class="error">${error}</p>
  </c:if>
  <c:if test="${not empty logs}">
    <pre>${logs}</pre>
  </c:if>
</div>
</body>
</html>