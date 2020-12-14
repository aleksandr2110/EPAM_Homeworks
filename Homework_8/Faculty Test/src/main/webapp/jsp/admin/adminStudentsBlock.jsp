<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 13.10.2020
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Students block</title>
    <link rel="stylesheet" type="text/css" href="/css/style-admin.css">
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"--%>
          <%--integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"--%>
          <%--crossorigin="anonymous">--%>
    <%--<style type="text/css">--%>
        <%--.mainbackground {--%>
            <%--background: #363636;  /* black  grey  -  #B5B5B5; */--%>
            <%--overflow: hidden; /* Свойство overflow управляет отображением содержания блочного элемента */--%>
            <%--color: white;--%>
        <%--}--%>
    <%--</style>--%>
</head>
<body>
<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка 1.1 --%>
            <td style="width: 250px; text-align: left;">
                <p>Ваш логин: ${user.getLogin()}</p>
                <p>Ваше имя: ${user.getFirstName()}</p>
                <p>Ваше фамилия: ${user.getLastName()}</p>
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <h3><a href="/education-faculty.com.ua/admin">Страница администратора</a></h3>
                <p><%=new java.util.Date() %></p>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                    <h3><a href="/education-faculty.com.ua/admin/courses">Курсы проводимые в IT-Academy</a></h3>
                    <h3><a href="/education-faculty.com.ua/admin/students">Информация о студентах</a></h3>
            </td>
        </tr>
        <tr>
            <td>
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/welcome_to_IT.jpg" align="left" width = "380" height="200" alt="" >
            </td>

            <td style="width: 900px">
                    <p>Студенты</p>
                    <table cellpadding="5" border="2">
                        <tr>
                            <td>Логин</td>
                            <td>Имя</td>
                            <td>Фамилия</td>
                            <td>Телефон</td>
                            <td>Эл. почта</td>
                            <td>Статус</td>
                            <td>Розблокировать</td>
                            <td>Заблокировать</td>
                        </tr>
                        <c:forEach items="${listFacultyUserDto}" var="user">
                        <tr>
                            <td>${user.getLogin()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getTelephone()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.isUserBlocked()}</td>
                            <td>
                                <form id="unblockUser" method="post" action="/education-faculty.com.ua/admin/students/block-page/unblock">
                                    <input type="hidden" name="userIdUnblock" value="${user.getUserId()}"/>
                                    <button type="submit">Разблокировать</button>
                                </form>
                            <td>
                            <form id="blockUser" method="post" action="/education-faculty.com.ua/admin/students/block-page/block">
                                <input type="hidden" name="userIdBlock" value="${user.getUserId()}"/>
                                <button type="submit">Заблокировать</button>
                            </form>
                        </td>
                        </tr>
                        </c:forEach>
                    </table>

                <main class="m-3">
                    <div class="row col-md-6">
                        <nav aria-label="Navigation for users">
                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/education-faculty.com.ua/admin/students/block-page?currentPage=${currentPage-1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="1" end="${numberOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><a class="page-link">
                                                    ${i} <span class="sr-only">(current)</span></a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link"
                                                                     href="/education-faculty.com.ua/admin/students/block-page?currentPage=${i}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${currentPage lt numberOfPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/education-faculty.com.ua/admin/students/block-page?currentPage=${currentPage+1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </main>
            </td>
            </td>
        </tr>
    </table>
</div>
</body>
</html>


                <%--<main class="m-3">--%>
                    <%--<div class="row col-md-6">--%>
                        <%--<table class="table table-striped table-bordered table-sm">--%>
                            <%--<tr>--%>
                                <%--<th>Name</th>--%>
                                <%--<th>Population</th>--%>
                            <%--</tr>--%>

                            <%--<c:forEach items="${listFacultyUserDto}" var="user">--%>
                                <%--<tr>--%>
                                    <%--<td>${user.getFirstName()}</td>--%>
                                    <%--<td>${user.getLastName()}</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                        <%--</table>--%>
                    <%--</div>--%>

                    <%--<nav aria-label="Navigation for countries">--%>
                        <%--<ul class="pagination">--%>
                            <%--<c:if test="${currentPage != 1}">--%>
                                <%--<li class="page-item"><a class="page-link"--%>
                                                         <%--href="/education-faculty.com.ua/admin/students/block-page?currentPage=${currentPage-1}">Previous</a>--%>
                                <%--</li>--%>
                            <%--</c:if>--%>

                            <%--<c:forEach begin="1" end="${numberOfPages}" var="i">--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${currentPage eq i}">--%>
                                        <%--<li class="page-item active"><a class="page-link">--%>
                                                <%--${i} <span class="sr-only">(current)</span></a>--%>
                                        <%--</li>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<li class="page-item"><a class="page-link"--%>
                                                                 <%--href="/education-faculty.com.ua/admin/students/block-page?currentPage=${i}">${i}</a>--%>
                                        <%--</li>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</c:forEach>--%>

                            <%--<c:if test="${currentPage lt numberOfPages}">--%>
                                <%--<li class="page-item"><a class="page-link"--%>
                                                         <%--href="/education-faculty.com.ua/admin/students/block-page?currentPage=${currentPage+1}">Next</a>--%>
                                <%--</li>--%>
                            <%--</c:if>--%>
                        <%--</ul>--%>
                    <%--</nav>--%>
                <%--</main>--%>


