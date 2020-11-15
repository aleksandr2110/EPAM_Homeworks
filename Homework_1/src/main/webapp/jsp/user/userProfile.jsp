<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.09.2020
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet"  type="text/css" href="/css/style-user.css">

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class = "mainbackground">
    <table border = "1" >
        <tr>
            <td >
                <%--левая колонка первая строка 1.1 --%>
                <p>Ваше имя : ${user.getFirstName()}</p>
                <p>Ваша фамилия : ${user.getLastName()}</p>
                <p>Ваш логин: ${user.getLogin()}</p>
                <p>Ваша почта: ${user.getEmail()}</p>
                <p>Ваш телефон: ${user.getTelephone()}</p>
                <p><a href="/education-faculty.com.ua/registration/user-page/id=${user.getUserId()}">Моя страница</a></p>
                <%=new java.util.Date() %>
            </td>

            <td style="width: 750px">
                <%--правая колонка первая строка 1.2 --%>
                <%--<jsp:include page = "/jsp/welcome/apartments.jsp" flush="true" />--%>
                    <a href="/education-faculty.com.ua"> <img src="/img/courses.jpg" alt="home"> </a>
            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td>
                <button type="button" name="updateUser">Обновить данные пользователя</button>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
