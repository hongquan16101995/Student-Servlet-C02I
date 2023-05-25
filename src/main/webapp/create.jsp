<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/25/2023
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Form create student</h1>
<form action="/create" method="post">
    <label for="id">Id</label>
    <input type="text" id="id" name="id"><br>
    <label for="name">Name</label>
    <input type="text" id="name" name="name"><br>
    <label for="age">Age</label>
    <input type="text" id="age" name="age"><br>
    <label for="gender">Gender</label>
    <input type="text" id="gender" name="gender"><br>
    <label for="address">Address</label>
    <input type="text" id="address" name="address"><br>
    <button type="submit">Create</button>
    <button><a href="/">Back to home</a></button>
</form>
</body>
</html>
