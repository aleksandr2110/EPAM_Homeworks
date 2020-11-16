<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  UserPage: Александр
  DateApartment: 28.09.2020
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/javascript/login.js"> </script>

</head>
<body>

<%-- При первой загрузке страницы попадаем сюда --%>
<p>
    <c:if test="${absentData != null}" >
        <c:out value="${absentData}" /> <br />
    Логин:     <br />
    <input name="login" id="log" type="text" size="25" maxlength="30" value="${log}" /> <br />
        <span>Это поле не может быть пустым!</span></br>
    Пароль:    <br />
    <input name="password" id="pass" type="password" size="25" maxlength="30" value="${pas}" /> <br />
        <span>Это поле не может быть пустым!</span></br>
    </c:if>

    <c:if test="${absentData == null}" >
    Логин:     <br />
    <input name="login" type="text" size="25" maxlength="30" value="" /> <br />
        <span>Это поле не может быть пустым!</span></br>
    Пароль:    <br />
    <input name="password" type="password" size="25" maxlength="30" value="" /> <br />
        <span>Это поле не может быть пустым!</span></br>
    </c:if>

        <input name="remember" type="checkbox" value="yes" />Запомнить<br />
        <button type='button' onClick="checkOnEmptyFields()" id="Send">Вход</button>
</body>
</html>
