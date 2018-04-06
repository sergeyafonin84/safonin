
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

<form action="${pageContext.servletContext.contextPath}/DeleteUserServlet" method="post">
    Login : <input type="text" name="login"><br/>
    <input type="submit" name="delete user" value="delete user">
</form>

<form action="${pageContext.servletContext.contextPath}/DeleteUserServlet" method="post">
    <input type="submit" name="Back" value="Back">
</form>

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

</body>
</html>
