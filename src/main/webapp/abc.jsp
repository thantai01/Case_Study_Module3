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
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>price</th>
            <th>madeIn</th>
            <th>image</th>
            <th>quantity</th>
            <th>type</th>
        </tr>
        <c:forEach var="p" items="${listP}">
            <tr>
                <td><c:out value="${p.id}"/></td>
                <td><c:out value="${p.name}"/></td>
                <td><c:out value="${p.price}"/></td>
                <td><c:out value="${p.madeIn}"/></td>
                <td><img src="${p.image}" alt="" width="100px"></td>
                <td><c:out value="${p.quantity}"/></td>
                <td><c:out value="${p.idType}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
