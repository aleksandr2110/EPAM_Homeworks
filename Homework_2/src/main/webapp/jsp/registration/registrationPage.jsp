<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 09.27.2020
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>--%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/javascript/script-new-user.js"> </script>
    <link rel="stylesheet"  type="text/css" href="/css/style-login.css">
</head>
<body>
    <div class="mainContentBackground" style="background-color: #c7b39b">
        <a href="/education-faculty.com.ua"> <img src="/img/welcome_to_IT.jpg" alt="home"> </a>
        <p> <big> Пройдите простую регистрацию на нашем сайте </big> </p>

        <%-- action="${pageContext.request.contextPath}/profile" --%>
        <form name= "form1" method= "post" action="/education-faculty.com.ua/registration" id="HideForms">

            <%! String session_first_name=""; %>
            <%! String session_last_name=""; %>
            <%! String session_login=""; %>
            <%! String session_password=""; %>
            <%! String session_telephone=""; %>
            <%! String session_email=""; %>

            <p><label for ="FIRST_NAME">Имя </label> </p>
            <p> <%
                if(session.getAttribute("firstName")!= null)
                {
                    session_first_name = ("<p> Имя " + session.getAttribute("firstName") + "</p>");
                }
            %>
                <%= session_first_name %> </p>
            <p><input  type="text" name="first_name" id="FIRST_NAME" placeholder="Name" >
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <p><label for ="LAST_NAME">Фамилия </label> </p>
            <p> <%
                if(session.getAttribute("lastName")!= null)
                {
                    session_last_name = ("<p> Фамилия " + session.getAttribute("lastName") + "</p>");
                }
            %>
                <%= session_first_name %> </p>
            <p><input  type="text" name="last_name" id="LAST_NAME" placeholder="Surname" >
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <%-- Эта поле логин универсальное для базы данных  --%>
            <p><label for ="LOGIN">Логин </label> </p>
            <p> <%
                if(session.getAttribute("log")!= null)
                {
                    session_login = ("<p> Логин " + session.getAttribute("log") + " уже существует</p>");
                }
            %>
                <%= session_login %> </p>
            <p><input  type="text" name="login" id="LOGIN" placeholder="Login">
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <%-- Эта поле представляет пароль, может повторяться --%>
            <% if(session.getAttribute("password")!=null)
            {
                session_password = (String) session.getAttribute("password");
            }
            %>
            <p><label for ="PASSWORD">Пароль </label></p>
            <p><input type="password" name="password" id="PASSWORD" placeholder="Password">
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <% if(session.getAttribute("telephone")!= null)
            {
                session_telephone = (String) session.getAttribute("telephone");
            }
            %>
            <p><label for ="telephone">Телефон</label></p>
            <%--после placeholder required--%>
            <%--pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"--%>

            <p><input type ="text" name="telephone" id="telephone" placeholder="example telephone 380...." value = "<%= session_telephone %>" >
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <% if(session.getAttribute("email")!= null)
            {
                session_email = (String) session.getAttribute("email");
            }
            %>
            <p><label for ="email">Электронная почта</label></p>
            <p><input type="text" name="email" id="EMAIL" placeholder="Email" value = "<%= session_email %>" >
                <span>Это поле не может быть пустым!</span></br>
            </p>

            <% session.invalidate(); %>
            <button type = "button" id="Save" name="baza" onClick = "BeforeSave()"> Зарегистрироваться  </button>

        </form>
    </div>

</body>
</html>
