<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 29.09.2020
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Course</title>
    <script type="text/javascript" src="/javascript/script-admin.js"></script>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"--%>
          <%--integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"--%>
          <%--crossorigin="anonymous">--%>
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
                <p><h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3></p>
                <p><h3><a href="/education-faculty.com.ua/admin">Страница администратора</a></h3></p>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                    <h3><a href="/education-faculty.com.ua/admin/students">Зачисление студентов</a></h3>
                    <h3><a href="/education-faculty.com.ua/admin/students/block-page">Блокировка студентов</a></h3>
                <button type="button" name="createCourseButton" id="createCourseButton" onClick="showCreteCourse()">Создать новый курс</button>
                    <form name="createCourseForm" id="createCourseForm"  method="post" action="/education-faculty.com.ua/admin/courses/create-course" >

                        <p><label for="CourseName">Название курса</label></p>
                        <p><input type = "text" placeholder="courseName" name="courseName" id="CourseName"></p>
                        <span>Это поле не может быть пустым!</span></br>

                        <p><label>Дата и время начала</label></p>
                        <input type="datetime-local" id="datetime" name="datetime">
                        <%--<p><input type = "datetime" placeholder="YYYY-MM-DDThh:mm" name="Datetime" id="Datetime"></p>--%>
                        <span>Это поле не может быть пустым!</span></br>

                        <p><label>Продолжительность кол-во недель</label></p>
                        <p><input type = "text" placeholder="duration" name="duration" id="duration"></p>
                        <span>Это поле не может быть пустым!</span></br>

                        <p><label>Цена курса</label></p>
                        <p><input type = "text" placeholder="price" name="price" id="price"></p>
                        <span>Это поле не может быть пустым!</span></br>

                        <p><label>Преподаватель</label></p>
                        <p><select name="teacher">
                            <c:forEach items="${teachersDto}" var="teacher">
                                <option value="${teacher.getUserId()}">${teacher.getLogin()}</option>
                            </c:forEach>
                        </select></p>
                        <span>Это поле не может быть пустым!</span></br>

                        <p><button type = "button" id="save_course" onClick="saveCourse()">Сохранить курс</button>
                            <%--<button type = "submit" >Сохранить курс</button>--%>
                        <button type="reset">Очистить</button></p>
                    </form>

                    <p>
                    <form id="sortFromAtoZ" method="post" action="/education-faculty.com.ua/admin/courses/courses-asc">
                        <input type="hidden" name="asc" value="ASC">
                        <button type="submit" class="b1">Сортировка названий курсов по возрастанию</button>
                    </form>
                    <form id="sortFromZtoA" method="post" action="/education-faculty.com.ua/admin/courses/courses-desc">
                        <input type="hidden" name="desc" value="DESC">
                        <button type="submit" class="b1">Сортировка названий курсов по убыванию</button>
                    </form>
                    <form id="chooseCoursesByTopic" method="post" action="/education-faculty.com.ua/admin/courses/courses-by-topic">
                        <select name="topicId">
                            <c:forEach items="${listTopic}"  var="topic">
                                <option value = "${topic.getTopicId()}">${topic.getTopicName()}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Выборка курсов определенной темы</button>
                    </form>

                    <form id="chooseCoursesByTeacher" method="post" action="/education-faculty.com.ua/admin/courses/courses-by-teacher">
                        <select name="teacherId">
                            <c:forEach items="${teachersDto}"  var="teacher">
                                <option value = "${teacher.getUserId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Выборка курсов определенной преподавателя</button>
                    </form>
                    </p>

                    <c:if test = "${topicIdFromReq != null}">
                        <div>
                            Курсы по теме ${descriptionTopics.get(topicIdFromReq)}
                        </div>
                    </c:if>

                    <c:if test = "${teacherDto != null}">
                        <div>
                            Курсы преподавателя ${teacherDto.getFirstName()}  ${teacherDto.getLastName()}
                        </div>
                    </c:if>
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
                                <td><a href="/education-faculty.com.ua/admin/course/id=${course.getCourseId()}">${course.getCourseName()}</a></td>
                                <td>${course.getDateTime()}</td>
                                <td>${course.getDurationWeeks()}</td>
                                <td>${course.getStudentCount()}</td>
                                <td>${course.getPrice()}</td>
                                <td>${course.getStatus()}</td>
                                <td>${mapTeachers.get(course.getUserId()).getFirstName()} ${mapTeachers.get(course.getUserId()).getLastName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
            </td>
        </tr>
    </table>
</div>
<% session.removeAttribute("teacherDto"); %>
<% session.removeAttribute("teacherIdFromReq"); %>
<% session.removeAttribute("listCourses"); %>
</body>
</html>
