
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 06.10.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<link rel="stylesheet" type="text/css" href="<c:url value='/css/style-user.css'/>">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<div class="menu">
    <form id="lang">
        <select id="language" name="lang" onchange="submit()" class="btn btn-primary">
            <option value="ru">Russian</option>
            <option value="en">English</option>
        </select>
    </form>
    <form id="login-button" action="${pageContext.request.contextPath}/login" method="get">
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>
    <form id="reg-button" action="${pageContext.request.contextPath}/registration" method="get">
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form>
</div>

<%--</body>--%>
<%--</html>--%>
