<%@ page import="ru.job4j.crudservlet.UserStore" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Work with user servlet</title>
</head>
<body>

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
    <% for (User user : UserStore.getInstance().getAllSql()) {%>
    <tr>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getName()%>
        </td>
    </tr>
    <% }%>
</table>

</body>
</html>
