<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 27.09.2020
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Admin main page</title>
    <script type="text/javascript" src="/javascript/script-admin.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/style-admin.css">
</head>
<body>

<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка with DTO 1.1 --%>
            <td style="width: 250px; text-align: left;">
                <p>Ваш логин: ${user.getLogin()}</p>
                <p>Ваш имя: ${user.getFirstName()}</p>
                <p>Ваш фамилия: ${user.getLastName()}</p>
                <p>Ваш логин: ${user.getLogin()}</p>
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                <h3><a href="/education-faculty.com.ua/admin/students">Зачисление студентов</a></h3>
                <h3><a href="/education-faculty.com.ua/admin/students/block-page">Блокировка студентов</a></h3>
                <h3><a href="/education-faculty.com.ua/admin/courses">Перейти к курсам</a></h3>
            </td>
        </tr>

        <tr>
            <td style="width: 250px">
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/courses_web.jpg" align="left" width = "380" height="200" alt="" >
            </td>

            <td style="width: 900px">
                <%-- правая колонка вторая строка 2.2 --%>
                <h2 align = "center">Поиск пользователя: </h2>

                        <%-- !!!!!!!!! поиск пользователя по логину!!!!!!!!!! --%>
                    <p>${userAbsent}</p>
                    <p>${loginAbsent}</p>
                <form name="search_login" id = "search_on_login"  method="post" action="/education-faculty.com.ua/admin/search-user" >
                     <p> <label for="number">По логину </label>  </p>
                     <input type = "text" placeholder="Login" name="login" id="number">
                     <button type = "button" id="send_login" onclick="sendFormLogin()">Поиск</button>
                     <button type="reset">Очистить</button>
                </form>
                    <table cellpadding="5" border="2">
                        <tr>
                            <td>Логин</td>
                            <td>Роль</td>
                        </tr>
                        <tr>
                            <td>${facultyUserDto.getLogin()}</td>
                            <td>${facultyUserDto.getRole()}</td>
                            <td>
                            <form name="assign" method="post" action="/education-faculty.com.ua/admin/assign-teacher">
                                <input type = "hidden" name="teacher" id="teacher" value="${facultyUserDto.getLogin()}">
                                <button type = "button" id="assign_teacher" onclick="createTeacher()">Назначить преподавателя</button>
                            </form>
                            </td>
                        </tr>
                    </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
