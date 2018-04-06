
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<form action="${pageContext.servletContext.contextPath}/AddRoleServlet" method="post">
    Role name : <input type="text" name="rolename"><br/>
    User login : <input type="text" name="userlogin"><br/>
    <input type="submit" name="create role" value="create role">
</form>

<form action="${pageContext.servletContext.contextPath}/AddRoleServlet" method="post">
    <input type="submit" name="Back" value="Back">
</form>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>rolename</th>
        <th>userlogin</th>
    </tr>
    <c:out value="Current user login: ${login}"></c:out>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td><c:out value="${role.rolename}"></c:out></td>
            <td><c:out value="${role.userlogin}"></c:out></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>