<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/9/2021
  Time: 8:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Managerment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%
    User admin = (User) session.getAttribute("user");
    String loginTime = (String) session.getAttribute("loginTime");
    String fullName = admin.getUserID();
%>
    <div class="container-fluid">
        <div class="row">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="https://britannia.edu.bd/admin/img/club/00fce5450c.png" alt="" width="150px" height="100px">
                    </a>
                    <a class="navbar-brand" href="#" disabled="">Management Site</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">User Management</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/productManager">Product Management</a>
                            </li>
<%--                            <li class="nav-item">--%>
<%--                                <a class="nav-link" href="#">Order Management</a>--%>
<%--                            </li>--%>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
        <div class="row">
              <span><p> WELCOME: <%=fullName%> Login Time: <%=loginTime%></p></span><hr>
        </div>
        <div class="row">
            <div class="col-8">
                <h3>User Management</h3>
                <div class="container-fluid">
                    <div class="row mt-3">
                        <form method="post">
                            <label>New Admin
                                <input type="text" name="userID" style="width: 100px">
                            </label>
                            <label> Password
                                <input type="password" name="password" style="width: 100px">
                            </label>
                            <input type="submit" name="action" value="Create" style="width: 100px">
                        </form>
                        <form method="get">
                            <label> Search
                                <input type="search" placeholder="User Name" name="userName" style="width: 200px">
                            </label>
                            <input type="submit" name="action" value="Search">
                        </form>
                        <hr>
                    </div>
                    <div class="row">
                        <table  class="table table-bordered" style="text-align: center">
                            <tr><h3>User List</h3></tr>
                            <tr>
                                <th>User Name</th>
                                <th>User Password</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="user" items="${requestScope['users']}">
                                <tr>
                                    <td>${user.userID}</td>
                                    <td>${user.userPassword}</td>
                                    <td>${user.role}</td>
                                    <td>
                                        <a class="btn btn-primary" style="background-color: #3b5998;" href="<c:url value="/userManager?action=edit&userName=${user.userID}"/>" role="button">Edit</a>
                                        <a class="btn btn-primary" style="background-color: #55acee;" href="<c:url value="/userManager?action=delete&userName=${user.userID}"/>" role="button">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
        <div class="row"></div>
    </div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
