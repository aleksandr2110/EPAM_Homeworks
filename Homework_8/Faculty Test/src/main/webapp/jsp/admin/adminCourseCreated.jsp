<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 01.10.2020
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/javascript/course-script.js"></script>
    <%--<script type="text/javascript" src="/javascript/script-modification-course-topic.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="/css/style-admin.css">

</head>
<body>
<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка with DTO 1.1--%>
            <td style="width: 250px; text-align: left;">
                <p>Ваш логин: ${user.getLogin()}</p>
                <p>Ваш имя: ${user.getFirstName()}</p>
                <p>Ваш фамилия: ${user.getLastName()}</p>
                <p>Ваш логин: ${user.getLogin()}</p>
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <h3><a href="/education-faculty.com.ua/admin">Страница администратора</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>
            <%--правая колонка первая строка 1.2 --%>
            <td style="width: 900px">
                <h3><a href="/education-faculty.com.ua/admin/courses">Перейти к курсам</a></h3>
                <h3><a href="/education-faculty.com.ua/admin/students">Зачисление студентов</a></h3>
                <h3><a href="/education-faculty.com.ua/admin/students/block-page">Блокировка студентов</a></h3>
            </td>
        </tr>

        <tr>
            <%-- левая колонка вторая строка 2.1 --%>
            <td style="width: 250px">
                <img src="/img/courses_web.jpg" align="left" width = "380" height="200" alt="" >
            </td>
            <%-- правая колонка вторая строка 2.2 --%>
            <td style="width: 900px">

                <table cellpadding="5" border="2">
                    <tr>
                        <td>Название курса</td>
                        <td>Дата и время начала</td>
                        <td>Продолжительность кол-во недель</td>
                        <td>Количество студентов</td>
                        <td>Цена курса</td>
                        <td>Преподаватель</td>
                        <td>Статус</td>
                        <td></td>
                    </tr>
                    <tr id="existedСourse">
                        <td>${course.getCourseName()}</td>
                        <td>${course.getDateTime()}</td>
                        <td>${course.getDurationWeeks()}</td>
                        <td>${course.getStudentCount()}</td>
                        <td>${course.getPrice()}</td>
                        <td>${teacher.getFirstName()} ${teacher.getLastName()}</td>
                        <td>${course.getStatus()}</td>
                        <form id="courseDelete" method="post" action="education-faculty.com.ua/admin/course/delete-course">
                            <input type="hidden" name="courseIdForDel" value="${course.getCourseId()}"/>
                            <td><button type="submit">Удалить курс</button></td>
                        </form>
                        <td><button type="button" onclick="updateCourse()">Редактировать</button></td>

                    </tr>
                </table>
                <form id="courseUpdate" method ="post" action="/education-faculty.com.ua/admin/course/update-course">
                    <p><label>Название курса</label></p>
                    <p></p><input type="text" name="courseName" id="CourseName" value="${course.getCourseName()}"/>
                    <span>Это поле не может быть пустым!</span></br>

                    <p><label>Дата и время начала</label></p>
                    <input type="datetime-local" name="dateTime" value="${course.getDateTime()}"/>
                    <span>Это поле не может быть пустым!</span></br>

                    <p><label>Продолжительность кол-во недель</label></p>
                    <p><input type = "text" placeholder="duration" name="duration" value="${course.getDurationWeeks()}"/></p>
                    <span>Это поле не может быть пустым!</span></br>

                    <p><label>Цена курса</label></p>
                    <p><input type = "text" placeholder="price" name="price" id="price" value="${course.getPrice()}"/></p>
                    <span>Это поле не может быть пустым!</span></br>

                    <p><label>Преподаватель</label></p>
                    <p><select name="teacher">
                        <c:forEach items="${listTeachersDto}" var="teacher">
                            <option value="${teacher.getUserId()}">${teacher.getFirstName()} ${teacher.getLastName()}</option>
                        </c:forEach>
                    </select></p>

                    <p><label>Статус курса</label></p>
                    <p><select name="status">
                        <c:forEach items="${statusList}" var="statusCourse">
                            <option value="${statusCourse}">${statusCourse}</option>
                        </c:forEach>
                    </select></p>

                    <p><input type="hidden" name="courseId" value="${course.getCourseId()}"/></p>
                    <p><button type="button" id="saveCourse"  onclick="submitCourse()" >Обновить данные курса</button>
                </form>

                <table id="showTopics" cellpadding="5" border="2">
                        <c:forEach items="${listTopicsPerCourse}" var="courseTopic">
                            <tr >
                                <th><option id="topic" value="${courseTopic.getTopicName()}">${courseTopic.getTopicName()}</option></th>
                                <td><button type="button" onclick="updateTopic()">Редактировать</button></td>

                                <form id="topicDelete" method="post" action="education-faculty.com.ua/admin/course/delete-topic">
                                    <input type="hidden" name="topicIdInTopicDel" value="${courseTopic.getTopicId()}"/>
                                    <%--<input type="hidden" name="courseIdInCourseDel" value="${courseTopic.getCourseId()}"/>--%>
                                    <td><button type="submit">Удалить тему</button></td>
                                </form>
                            </tr>
                        </c:forEach>
                </table>
                <!-- form topicUpdate -->

                <form id="topicUpdate" method ="post" action="/education-faculty.com.ua/admin/course/update-topic">
                <c:forEach items="${listTopicsPerCourse}" var="courseTopic">
                    <p>
                    <select name="topic_update" id="topic_update">

                        <optgroup label="${course.getCourseName()}">
                            <c:forEach items="${listOfTopics}" var="topic">
                                <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                            </c:forEach>
                        </optgroup>

                    </select>
                    </p>
                </c:forEach>
                    <input type="hidden" name="topicInCourseDel" value="${course.getCourseId()}">
                    <p><button type="submit" >Обновить данные темы</button>
                </form>


                <button type="button" id="createTopics" onClick="addTopic()">Добавить тему №1</button>
                <form id="addTopicsForm" method="post" action="/education-faculty.com.ua/admin/course/add-topics">
                        <select name="topic_1" id="topic_1">
                            <optgroup label="${course.getCourseName()}">
                            <c:forEach items="${listOfTopics}" var="topic">
                                <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                            </c:forEach>
                            </optgroup>
                        </select>
                        <br><button type="button" id="button_2" onClick="addTopic2()">Добавить тему №2</button>
                        <select name="topic_2" id="topic_2">
                                <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                                </optgroup>
                       </select>

                        <button type="button"  id="button_3"  onClick="addTopic3()">Добавить тему №3</button>
                        <select name="topic_3" id="topic_3">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_4"  onClick="addTopic4()">Добавить тему №4</button>
                        <select name="topic_4" id="topic_4">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_5"  onClick="addTopic5()">Добавить тему №5</button>
                        <select name="topic_5" id="topic_5">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_6"  onClick="addTopic6()">Добавить тему №6</button>
                        <select name="topic_6" id="topic_6">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_7"  onClick="addTopic7()">Добавить тему №7</button>
                        <select name="topic_7" id="topic_7">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_8"  onClick="addTopic8()">Добавить тему №8</button>
                        <select name="topic_8" id="topic_8">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_9"  onClick="addTopic9()">Добавить тему №9</button>
                        <select name="topic_9" id="topic_9">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>

                        <button type="button"  id="button_10"  onClick="addTopic10()">Добавить тему №10</button>
                        <select name="topic_10" id="topic_10">
                            <optgroup label="${course.getCourseName()}">
                                <c:forEach items="${listOfTopics}" var="topic">
                                    <option value="${topic.getTopicId()}">${topic.getTopicName()}</option>
                                </c:forEach>
                            </optgroup>
                        </select>
                        <p><button type = "submit" >Закрепить темы за курсом</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
