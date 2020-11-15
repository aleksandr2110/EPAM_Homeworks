<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 05.10.2020
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"  type="text/css" href="/css/style-user.css">
    <script type="text/javascript" src="/javascript/script-user.js"></script>
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
                <p><a href="/education-faculty.com.ua/registration/user-page/id=${user.getUserId()}">Моя страница</a></p>
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
                <img src="/img/courses.jpg" align="left" width = "380" height="200" alt=""/>
            </td>

            <td style="width: 900px">
                <table cellpadding="5" border="2">
                    <tr>
                        <td>Название курса</td>
                        <td>Дата и время начала</td>
                        <td>Продолжительность кол-во недель</td>
                        <td>Количество студентов</td>
                        <td>Цена курса грн.</td>
                        <td>Преподаватель</td>
                        <td>Номер заявки</td>
                    </tr>
                    <tr>
                        <td>${course.getCourseName()}</td>
                        <td>${course.getDateTime()}</td>
                        <td>${course.getDurationWeeks()}</td>
                        <td>${course.getStudentCount()}</td>
                        <td>${course.getPrice()}</td>
                        <td>${teacher.getFirstName()} ${teacher.getLastName()}</td>
                        <c:set var = "applyUser" value = "${numberApplyUser}"/>
                        <td>
                            <c:if test = "${applyUser > 0}">
                               <c:out value = "${applyUser}"/>
                            </c:if>
                        </td>
                        <%--<% session.removeAttribute("numberApplyUser"); %>--%>
                    </tr>
                </table>
                <c:set var = "applyUser" value = "${numberApplyUser}"/>
                <c:if test = "${applyUser == 0}">
                    <form id="formRegOnCourse" method="post" action="/education-faculty.com.ua/user/course-detail/registration-on-course">
                        <input type="hidden" name="registrationCourseId" value="${course.getCourseId()}"/>
                        <input type="hidden" name="registrationUserId" value="${user.getUserId()}"/>
                        <button type="submit">Зарегистрироваться на курс</button>
                    </form>
                </c:if>

                <p>Список тем</p>
                <ol>
                <c:forEach items="${listTopicsPerCourse}" var="courseTopic">
                        <li>${courseTopic.getTopicName()}</li>
                </c:forEach>
                </ol>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
