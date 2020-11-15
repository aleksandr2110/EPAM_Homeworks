<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 15.10.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <style type="text/css">
        body {
            bacground-color: lightblue;
        }
        h3 {
            color : red;
        }
        p {
            color : red;
        }
    </style>
</head>
<body>
       <h3><a href="/education-faculty.com.ua">Стартовая страница</a></h3>
       <c:if test="${inputValidation != null}">
           <h3><c:out value="${inputValidation}"/></h3>
       </c:if>
       <% session.removeAttribute("inputValidation"); %>

       <c:if test="${crudException != null}">
           <h3><c:out value="${crudException}"/></h3>
       </c:if>
       <% session.removeAttribute("crudException"); %>
</body>
</html>
