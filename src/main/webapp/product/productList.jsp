<%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/7/2021
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
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
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th>5</th>
                <th>6</th>
                <th>7</th>
                <th>Action</th>
            </tr>
            <c:forEach var="product" items="${requestScope['listP']}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.madeIn}</td>
                    <td>${product.image}</td>
                    <td>${product.quantity}</td>
                    <td>${product.idType}</td>
                    <td>
                        <a class="btn btn-primary" style="background-color: #3b5998;" href="<c:url value="/productManager?action=edit&userID=${product.id}"/>" role="button">Edit</a>
                        <a class="btn btn-primary" style="background-color: #55acee;" href="<c:url value="/productManager?action=delete&userID=${product.id}"/>" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>
