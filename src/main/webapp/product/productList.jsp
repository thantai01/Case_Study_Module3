<%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/7/2021
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <table  class="table table-bordered" style="text-align: center">
            <tr><h3>Product List</h3></tr>
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Giá</th>
                <th>Xuất Sứ</th>
                <th>Hình Ảnh</th>
                <th>Số lượng</th>
                <th>Mã Loại</th>
                <th>Action</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.madeIn}</td>
                    <td><img src="${product.image}" width="20%"></td>
                    <td>${product.quantity}</td>
                    <td>${product.idType}</td>
                    <td>
                        <a href="/productManager?action=create">Create</a>
                        <a href="/productManager?action=edit&id=${product.id}">Edit</a>
                        <a href="/productManager?action=delete&id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>
