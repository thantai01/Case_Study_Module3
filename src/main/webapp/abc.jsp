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
        <c:forEach var="o" items="${listOrder}">
            <tr>
                <td><c:out value="${o.id}"/></td>
                <td><c:out value="${o.userName}"/></td>
                <td><c:out value="${o.time}"/></td>
            </tr>
            <tr>
            <c:forEach var="od" items="${listDetail}">
            <tr>
                <td><c:out value="${od.idProduct}"/></td>
                <td><c:out value="${od.idOrder}"/></td>
                <td><c:out value="${od.quantity}"/></td>
            </tr>

            </c:forEach>
            <tr>
                <c:forEach items="${listProduct}" var="p">
                    <td><c:out value="${p.name}"/></td>
                    <td><c:out value="${p.madeIn}"/></td>
                    <td><img src="${p.image}" alt="" width="100px"></td>
                    <td><c:out value="${p.quantity}"/></td>
                </c:forEach>
            </tr>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
