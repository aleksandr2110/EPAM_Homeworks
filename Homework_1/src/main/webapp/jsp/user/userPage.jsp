<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 29.09.2020
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User page</title>
    <link rel="stylesheet"  type="text/css" href="/css/style-user.css">
</head>
<body>
<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка 1.1 --%>
            <td style="width: 250px; text-align: left;">
                <p>Ваше имя: ${user.getFirstName()}</p>
                <p>Ваше фамилия: ${user.getLastName()}</p>
                <p>Ваш логин: ${user.getLogin()}</p>
                <p><a href="/education-faculty.com.ua">Стартовая страница</a></p>
                <p><a href="/education-faculty.com.ua/registration/user-profile/id=${user.getUserId()}">Анкетные данные</a></p>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                    <h3><a href="/education-faculty.com.ua/user/courses">Курсы проводимые в IT-Academy</a></h3>
                    <h3><a href="/education-faculty.com.ua/user/not-started-courses">Курсы ожидающие вашего участия</a></h3>
                    <h3><a href="/education-faculty.com.ua/user/started-courses">Текущие курсы</a></h3>
                    <h3><a href="/education-faculty.com.ua/user/finished-courses">Законченные курсы</a></h3>
            </td>
        </tr>
        <tr>
            <td>
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/courses.jpg" align="left" width = "380" height="200" alt="" >
                <%--<jsp:include page="info.jsp" flush="true"/>--%>
            </td>

            <td style="width: 900px">
                <div>
                </div>

            </td>
        </tr>
    </table>
</div>
</body>
</html>
