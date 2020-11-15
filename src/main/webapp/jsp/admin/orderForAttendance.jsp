<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 12.10.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"  type="text/css" href="/css/style-admin.css">
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
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <h3><a href="/education-faculty.com.ua/admin">Страница администратора</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                    <h3><a href="/education-faculty.com.ua/admin/courses">Курсы проводимые в IT-Academy</a></h3>
                    <h3><a href="/education-faculty.com.ua/admin/students">Информация о студентах</a></h3>
                    <h3><a href="/education-faculty.com.ua/admin/students/block-page">Блокировка студентов</a></h3>
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
                        <td>Идентификатор на поступление</td></td>
                        <td>Название курса</td>
                        <td>Имя студента</td>
                        <td>Фамилия студента</td>
                        <td>Телефон студента</td>
                        <td>Студент зачислен</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${listRegistration}" var="registrNotApproved">
                    <tr>
                            <td>${registrNotApproved.getRegistrationId()}</td>
                            <td>${coursesMap.get(registrNotApproved.getCourseId()).getCourseName()}</td>
                            <td>${facultyUsersMap.get(registrNotApproved.getUserId()).getFirstName()}</td>
                            <td>${facultyUsersMap.get(registrNotApproved.getUserId()).getLastName()}</td>
                            <td>${facultyUsersMap.get(registrNotApproved.getUserId()).getTelephone()}</td>
                            <td>${registrNotApproved.isApprove()}</td>
                            <td>
                                <form id="formApproveOnCourse" method="post" action="/education-faculty.com.ua/admin/students/approve">
                                    <input type="hidden" name="registrationCourseId" value="${registrNotApproved.getCourseId()}"/>
                                    <input type="hidden" name="registrationUserId" value="${registrNotApproved.getUserId()}"/>
                                    <button type="submit">Подтвердить поступление</button>
                                </form>
                            </td>
                    </tr>
                    </c:forEach>
                </table>
                <%--<form id="formRegOnCourse" method="post" action="/education-faculty.com.ua/user/course-detail/registration-on-course">--%>
                    <%--<input type="hidden" name="registrationCourseId" value="${course.getCourseId()}"/>--%>
                    <%--<input type="hidden" name="registrationUserId" value="${user.getUserId()}"/>--%>
                    <%--<button type="submit">Зарегистрироваться на курс</button>--%>
                <%--</form>--%>
                <%--<p>Список тем</p>--%>
                <%--<ol>--%>
                    <%--<c:forEach items="${listTopicsPerCourse}" var="courseTopic">--%>
                        <%--<li>${courseTopic.getTopicName()}</li>--%>
                    <%--</c:forEach>--%>
                </ol>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
