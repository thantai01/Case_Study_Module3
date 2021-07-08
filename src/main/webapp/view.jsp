<%--
  Created by IntelliJ IDEA.
  User: ACER PC
  Date: 7/8/2021
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Tên khách: ${order.userName}</h1>
<h1>Thời gian: ${order.time}</h1>
<c:forEach var="i" begin="0" end="${orderDs.size()-1}">
    Tên sản phẩm: ${products.get(i).getName()}
    Số lượng: ${orderDs.get(i).getQuantity()}
    <hr>
</c:forEach>
</body>
</html>
