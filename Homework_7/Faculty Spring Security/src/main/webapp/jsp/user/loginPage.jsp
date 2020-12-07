<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.09.2020
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="/WEB" prefix="custom"%>--%>
<%@ taglib uri="/WEB-INF/tag/Authorization.tld" prefix="custom" %>

<html>
<head>
    <title>Log in</title>
    <%--<link rel="stylesheet"  type="text/css" href="/css/style-login.css">--%>
    <script type="text/javascript" src="/javascript/login.js"> </script>
    <style type="text/css">
        input {
            background: #DCDCDC;
        }
        p {
            color:#2F4F4F
        }
        button {
            color:white;
            /*border-radius: 8px;*/
            padding: 2px 10px;
            color:#006400
        }
    </style>
</head>
<body>
<strong>
    <a href="/education-faculty.com.ua"><img src="/img/courses_advertisement.jpg" align="left" alt="Home" width = "580" height="217"  /> </a>
    <form name="loginForm" method="post" action="/education-faculty.com.ua/login">
        <c:if test = "${login == null}">
            <jsp:include page = "/jsp/registration/withoutLogin.jsp" flush="true" />
        </c:if>
    </form>>
    <
<%--<p style="color:#800000"><custom:hello role = "${user.getRole()}"/></h3>--%>
<%--</p>--%>

</strong>
</body>
</html>
