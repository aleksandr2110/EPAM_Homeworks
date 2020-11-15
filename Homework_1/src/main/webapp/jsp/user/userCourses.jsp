<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 05.10.2020
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet"  type="text/css" href="/css/style-user.css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка 1.1 --%>
            <td style="width: 250px; text-align: left;">
                <p>Ваше имя: ${user.getFirstName()}</p>
                <p>Ваше имя: ${user.getLastName()}</p>
                <p>Ваш логин: ${user.getLogin()}</p>
                <p>Ваша эл. почта: ${user.getEmail()}</p>
                <p>Ваш телефон: ${user.getTelephone()}</p>
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <h3><a href="/education-faculty.com.ua/registration/user-profile/id=${user.getUserId()}">Мой профиль</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                <p>
                    <h3><a href="/education-faculty.com.ua/user/not-started-courses">Курсы ожидающие вашего участия</a></h3>
                    <h3><a href="/education-faculty.com.ua/user/started-courses">Текущие курсы</a></h3>
                    <h3><a href="/education-faculty.com.ua/user/finished-courses">Законченные курсы</a></h3>

                    <form id="sortFromAtoZ" method="post" action="/education-faculty.com.ua/user/courses/courses-asc">
                        <input type="hidden" name="asc" value="ASC">
                        <button type="submit" class="b1">Сортировка по возрастанию</button>
                    </form>
                    <form id="sortFromZtoA" method="post" action="/education-faculty.com.ua/user/courses/courses-desc">
                        <input type="hidden" name="desc" value="DESC">
                        <button type="submit" class="b1">Сортировка по убыванию</button>
                    </form>
                    <form id="chooseCoursesByTopic" method="post" action="/education-faculty.com.ua/user/courses/courses-by-topic">
                        <select name="topicId">
                            <c:forEach items="${listTopic}"  var="topic">
                                <option value = "${topic.getTopicId()}">${topic.getTopicName()}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Выборка курсов определенной темы</button>
                    </form>
                    <form id="chooseCoursesByTeacher" method="post" action="/education-faculty.com.ua/user/courses/courses-by-teacher">
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
            <td>
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/courses.jpg" align="left" width = "380" height="200" alt="" >
                <%--<jsp:include page="info.jsp" flush="true"/>--%>
            </td>

            <td style="width: 900px">
                <table cellpadding="5" border="2">
                    <tr>
                        <td>Название курса</td>
                        <td>Дата и время начала</td>
                        <td>Продолжительность кол-во недель</td>
                        <td>Количество студентов</td>
                        <td>Цена курса грн.</td>
                        <td>Статус</td>
                        <td>Преподаватель</td>
                    </tr>
                    <c:forEach items="${listCourses}" var="course">
                    <tr>
                        <td><a href="/education-faculty.com.ua/user/course-detail/id=${course.getCourseId()}">${course.getCourseName()}</a></td>
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
    <% session.removeAttribute("teacherDto"); %>
    <% session.removeAttribute("topicIdFromReq"); %>
    <% session.removeAttribute("listCourses"); %>
</div>
</body>
</html>
