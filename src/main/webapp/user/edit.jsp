<%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/6/2021
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Site</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="row" style="height: 5%"></div>
    <div class="row">
        <form method="post">
            <!-- UserName input -->
            <div class="form-outline mb-4">
                <label for="form3Example1"> User Name</label>
                <input type="text" id="form3Example1" class="form-control" name="userName" disabled value="${requestScope['user'].userID}"/>
            </div>
            <!-- Password input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form3Example2">Password</label>
                <input type="password" id="form3Example2" class="form-control" name="userPassword" value="${requestScope['user'].userPassword}"/>
            </div>
            <!-- fullName input -->
            <div class="form-outline mb-4">
                <label for="form3Example3"> Address </label>
                <input type="text" id="form3Example3" class="form-control" name="address" value="${requestScope['user'].userAddress}"/>
            </div>
            <!-- Address input -->
            <div class="form-outline mb-4">
                <label for="form3Example5"> FullName </label>
                <input type="text" id="form3Example5" class="form-control" name="fullName" value="${requestScope['user'].userFullName}"/>
            </div>
            <!-- Avatar URL input -->
            <div class="form-outline mb-4">
                <label for="form3Example7"> Phone Number  </label>
                <input type="text" id="form3Example7" class="form-control" name="userPhoneNum" value="${requestScope['user'].userPhone}"/>
            </div> <!-- Avatar URL input -->
            <div class="form-outline mb-4">
                <label for="form3Example8"> Role </label>
                <input type="text" id="form3Example8" class="form-control" name="role" value="${requestScope['user'].role}"/>
            </div>
            <!-- Submit button -->
            <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 150px">Save </button>
        </form>
    </div>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
