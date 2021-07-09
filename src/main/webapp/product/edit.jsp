<%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/7/2021
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="productManager?action=productManager">Back</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Product
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
            </c:if>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" size="60"   value="${product.name}" />
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="text" name="price" size="60" value="<c:out value='${product.price}' />"/>
                </td>
            </tr>
            <tr>
                <th>madeIn:</th>
                <td>
                    <input type="text" name="madeIn"  size="60" value="<c:out value='${product.madeIn}' />"/>
                </td>
            </tr>
            <tr>
                <th>Image:</th>
                <td>
                    <input type="text" name="image"  size="60" value="<c:out value='${product.image}' />"/>
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td>
                    <input type="text" name="quantity"  size="60" value="<c:out value='${product.quantity}' />"/>
                </td>
            </tr>
            <tr>
                <th>Id Type:</th>
                <td>
                    <input type="text" name="idType"  size="60" value="<c:out value='${product.idType}' />"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
