<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="post">
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    <input type="submit">
</form>
<br/>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>email</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <%--<td><c:out value="${user.login}"></c:out></td>--%>
            <td><a href="${pageContext.servletContext.contextPath}/webapp/Items.html?id=${user.id}"><c:out value="${user.login}"></a></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
