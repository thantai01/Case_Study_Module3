<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER PC
  Date: 7/8/2021
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${orders}" var="order">
    <a href="/orders?action=view&id=${order.getId()}">${order.getId()}</a>
    <a href="/orders?action=view&id=${order.getId()}">${order.userName}</a>
    <hr>
</c:forEach>
</body>
</html>
