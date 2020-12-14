<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 24.09.2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<strong>
    <c:if test = "${user.getLogin() != null}">
        <c:out value = "${user.getLogin()}" />
    </c:if><br></p>
    <div>
        <c:if test = "${user.getRole() eq 'admin'}">
            <button type = 'button' onClick='location.href="/education-faculty.com.ua/admin"'>Страница админа</button>
        </c:if>
        <c:if test = "${user.getRole() eq 'user'}">
            <button type = 'button' onClick='location.href="/education-faculty.com.ua/registration/user-page/id=${user.getUserId()}"'>Моя страница</button>
        </c:if>
        <c:if test = "${user.getRole() eq 'teacher'}">
            <button type = 'button' onClick='location.href="/education-faculty.com.ua/registration/teacher-page/id=${user.getUserId()}"'>Моя страница</button>
        </c:if>
        <button type ='button' onClick='location.href="/education-faculty.com.ua/logout"'>Выход</button>
    </div>
    </p>
</strong>
</body>
</html>
