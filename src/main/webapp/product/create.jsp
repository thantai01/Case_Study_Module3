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
    <title>Create Product</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="users?action=users">Back</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add Product</h2>
            </caption>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" size="60" placeholder="Nhập tên loại hoa quả"/>
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="text" name="price" size="60" placeholder="Nhập giá"/>
                </td>
            </tr>
            <tr>
                <th>madeIn:</th>
                <td>
                    <input type="text" name="madeIn"  size="60" placeholder="Nhập xuất sứ"/>
                </td>
            </tr>
            <tr>
                <th>Image:</th>
                <td>
                    <input type="text" name="image"  size="60" placeholder="Nhập link ảnh"/>
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td>
                    <input type="text" name="quantity"  size="60" placeholder="Nhập số lượng"/>
                </td>
            </tr>
            <tr>
                <th>Id Type:</th>
                <td>
                    <input type="text" name="idType"  size="60" placeholder="Nhập mã loại"/>
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
