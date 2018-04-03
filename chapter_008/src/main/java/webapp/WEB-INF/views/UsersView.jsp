<%--<%@ page import="ru.job4j.parsentev.servlets.UserStorage" %>--%>
<%--<%@ page import="ru.job4j.parsentev.servlets.User" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<form action="<%=request.getContextPath()%>/" method="post">--%>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    <input type="submit">
</form>
<br/>

<%--<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">--%>
    <%--<tr>--%>
        <%--<th>login</th>--%>
        <%--<th>email</th>--%>
    <%--</tr>--%>
    <%--&lt;%&ndash;<% for (User user : UserStorage.getInstance().getUsers()) {%>&ndash;%&gt;--%>
    <%--<% for (User user : (List<User>) request.getAttribute("users")) {%>--%>
        <%--<tr>--%>
                <%--<td><%=user.getLogin()%></td>--%>
                <%--<td><%=user.getEmail()%></td>--%>
                <%--<td></td>--%>
        <%--</tr>--%>
        <%--<% }%>--%>
<%--</table>--%>


<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>email</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
