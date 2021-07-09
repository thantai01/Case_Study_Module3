<%--
  Created by IntelliJ IDEA.
  User: ACER PC
  Date: 7/6/2021
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/users?action=create">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <c:forEach items="${products}" var="p">
                <td><c:out value="${p.name}"/></td>
                <td><c:out value="${p.madeIn}"/></td>
                <td><img src="${p.image}" alt="" width="100px"></td>
                <td><c:out value="${p.quantity}"/></td>
            </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
