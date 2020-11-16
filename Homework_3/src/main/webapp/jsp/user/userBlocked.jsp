<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 15.10.2020
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User blocked</title>
    <link rel="stylesheet"  type="text/css" href="/css/style-user.css">

</head>
<body>
<div class="mainbackground">
    <table border="1" color="white">
        <tr>
            <%--левая колонка первая строка 1.1 --%>
            <td style="width: 250px; text-align: left;">
                <h3>Ваше имя: ${user.getFirstName()}</h3>
                <h3>Ваше фамилия: ${user.getLastName()}</h3>
                <h3>Ваш логин: ${user.getLogin()}</h3>
                <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
                <h3><%=new java.util.Date() %></h3>
            </td>

            <td style="width: 900px">
                <%--правая колонка первая строка 1.2 --%>
                <h3>Ваш аккаунт заблокирован, свяжитесь с администратором</h3>
            </td>
        </tr>
        <tr>
            <td>
                <%-- левая колонка вторая строка 2.1 --%>
                <img src="/img/courses.jpg" align="left" width = "380" height="200" alt="" >
                <%--<jsp:include page="info.jsp" flush="true"/>--%>
            </td>

            <td style="width: 900px">
                <div>
                </div>

            </td>
        </tr>
    </table>
</div>
</body>
</html>
