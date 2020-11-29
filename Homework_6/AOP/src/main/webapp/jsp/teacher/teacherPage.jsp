<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 29.09.2020
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/style-teacher.css">
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
                <p><h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3></p>
                <p><h3><a href="/education-faculty.com.ua/registration/teacher-profile/id=${user.getUserId()}">Анкетные данные</a></h3></p>
                <p><%=new java.util.Date() %></p>
            </td>

        </tr>
        <tr>
            <td style="width: 250px">
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/courses_web.jpg" align="left" width = "380" height="200" alt="" >
            </td>

            <td style="width: 900px">
                <%-- правая колонка вторая строка 2.2 --%>
                    <table cellpadding="5" border="2">
                        <tr>
                            <td>Название курса</td>
                            <td>Дата и время начала</td>
                            <td>Продолжительность кол-во недель</td>
                            <td>Количество студентов</td>
                            <td>Цена курса</td>
                            <td>Статус курса</td>
                            <td>Преподаватель</td>
                        </tr>
                        <c:forEach items="${listCourses}" var="course">
                            <tr>
                                <td><a href="/education-faculty.com.ua/teacher-page/course/id=${course.getCourseId()}">${course.getCourseName()}</a></td>
                                <td>${course.getDateTime()}</td>
                                <td>${course.getDurationWeeks()}</td>
                                <td>${course.getStudentCount()}</td>
                                <td>${course.getPrice()}</td>
                                <td>${course.getStatus()}</td>
                                <td>${user.getFirstName()} ${user.getLastName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
