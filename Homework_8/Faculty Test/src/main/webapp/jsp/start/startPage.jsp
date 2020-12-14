
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 24.09.2020
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<%@ page session="true" %>--%>
<%@ page isELIgnored="false"%>

<%--<%@ page import="java.util.Locale" %>--%>
<%--<%@ page import = "java.io.*,java.util.Locale" %>--%>
<%--<%@ page import = "javax.servlet.*,javax.servlet.http.* "%>--%>

<%--<fmt:setLocale value="ru"/>--%>

<%--<fmt:setLocale value="${sessionScope.lang}" />--%>
<%--<fmt:setLocale value="${param.lang}"/>--%>
<%--<fmt:setBundle basename="messages"/>--%>

<html lang="${param.lang}">
<%--<html lang="${sessionScope.lang}">--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<h2>--%>
    <%--<fmt:message key="label.chooseSessionLocale" />--%>
<%--</h2>--%>
<%--<p>--%>
    <%--<fmt:message key="label.requestLocaleContent" />--%>
<%--</p>--%>
<ul>
    <%--<li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>--%>
    <%--<li><a href="?lang=ru"><fmt:message key="label.lang.ru" /></a></li>--%>


    <%--<li><a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a></li>--%>
    <%--<li><a href="?sessionLocale=ru"><fmt:message key="label.lang.ru" /></a></li>--%>
</ul>
<strong>

    <form name="loginForm" >
        <c:set var="login" value = "${user.getLogin()}" />
        <c:if test = "${user.getLogin() != null}">
           <p>Логин: <jsp:include page = "/jsp/registration/withLogin.jsp" flush="true" /></p>
        </c:if>
        <c:if test = "${user.getLogin() == null}">
           <h3><a href="/education-faculty.com.ua/login">Авторизироваться</a><br></h3>
           <h3><a href="/education-faculty.com.ua/registration">Зарегистрироваться</a></h3>
        </c:if>

    </form>
        <%--Зарегистрироваться--%>
</strong>

<div>

</div>

<div id="al-c">
    <%--<fmt:message key="index.welcome"/><br/>--%>
    <div class="container">
        <ul class="effects">
            <li>
                <img src="/img/courses_central.jpg" align="left" width = "480" height="150" alt="" >
                <div>
                    <div id="al-b">
                        <div class="container">
                            <h2>Курсы по программированию</h2>
                            <a href="/education-faculty.com.ua/courses">Перейти к перечню</a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

</body>
</html>
