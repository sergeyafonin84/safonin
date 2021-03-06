<%@ page import="ru.job4j.crudservlet.UserStore" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Work with user servlet</title>
</head>
<body>

<br/>

<a href="http://localhost:8080/items/AddUserServlet">Add</a>
<br/>
<a href="http://localhost:8080/items/DeleteUserServlet">Delete</a>
<br/>
<a href="http://localhost:8080/items/EditUserServlet">Edit</a>
<br/>
<a href="http://localhost:8080/items/GetUserServlet">Get</a>

<br/>
<br/>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>email</th>
        <th>name</th>
    </tr>
    <c:out value="Current user login: ${login}"></c:out>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
        </tr>
    </c:forEach>
</table>

<br/>

<a href="http://localhost:8080/items/AddRoleServlet">Add role</a>

<br/>

<a href="http://localhost:8080/items/DeleteRoleServlet">Delete role</a>

<br/>
<br/>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>rolename</th>
        <th>userlogin</th>
    </tr>

    <c:forEach items="${roles}" var="role">
        <tr>
            <td><c:out value="${role.rolename}"></c:out></td>
            <td><c:out value="${role.userlogin}"></c:out></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
