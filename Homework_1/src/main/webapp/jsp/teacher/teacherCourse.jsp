<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 14.10.2020
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Teacher courses</title>
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
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
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
                        <tr>
                            <td>${course.getCourseName()}</td>
                            <td>${course.getDateTime()}</td>
                            <td>${course.getDurationWeeks()}</td>
                            <td>${course.getStudentCount()}</td>
                            <td>${course.getPrice()}</td>
                            <td>${course.getStatus()}</td>
                            <td>${user.getFirstName()} ${user.getLastName()}</td>
                        </tr>
                    </table>
                <h3><a href="/education-faculty.com.ua/registration/teacher-page/${user.getUserId()}">Перейти к курсам</a></h3>
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
                        <p>Студенты</p>
                        <tr>
                            <td>Имя</td>
                            <td>Фамилия</td>
                            <td>Телефон</td>
                            <td>Эл. почта</td>
                            <td>Статус блокирования</td>
                            <td>Оценка</td>
                            <td></td>
                        </tr>
                        <c:forEach items="${listUserDto}" var="user">
                        <tr>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getTelephone()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.isUserBlocked()}</td>
                            <td>${mapStudentMark.get(user.getUserId())}</td>
                            <c:if test = "${course.getStatus() eq 'Закончен'}">
                                <td>
                                    <form id="evaluateUser" method="post" action="/education-faculty.com.ua/teacher-page/course/evaluate">
                                        <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                        <input type="hidden" name="courseId" value="${course.getCourseId()}"/>
                                        <input type="text" name="evaluate" />
                                        <button type="submit">Выставить оценку</button>
                                    </form>
                                </td>
                            </c:if>
                            <c:if test = "${teacherDto != 'Закончен'}">
                               <td></td>
                            </c:if>
                        </tr>
                        </c:forEach>
                    </table>
                    <p>Список тем</p>
                    <ol>
                        <c:forEach items="${listTopicsPerCourse}" var="courseTopic">
                            <li>${courseTopic.getTopicName()}</li>
                        </c:forEach>
                    </ol>
            </td>
        </tr>
    </table>
    <% session.removeAttribute("listUserDto"); %>
    <% session.removeAttribute("mapStudentMark"); %>
    <% session.removeAttribute("listTopicsPerCourse"); %>
    <% session.removeAttribute("course"); %>
</div>
</body>
</html>
