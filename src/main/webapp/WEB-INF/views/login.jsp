<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Login</title>
</head>
<body>

<c:url var="regButton" value="/reg"></c:url>

<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0;">
            ${errorMessage}
    </div>
</c:if>

<div id="login">
    <h3 class="text-center text-white pt-5">Пройдите процесс авторизации.</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form"
                          name='login' action="<c:url value='/login'/>" method='POST'>
                        <h3 class="text-center text-primary">Пройдите процесс авторизации.</h3>
                        <div class="form-group">
                            <label for="username" class="text-primary">Логин:</label><br>
                            <input type="text" autocomplete="off" name='username' id="username" class="form-control"
                                   placeholder="Введите логин">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-primary">Пароль:</label><br>
                            <input type="password" autocomplete="off" name='password' id="password" class="form-control"
                                   placeholder="Введите пароль">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-primary btn-md" value="Войти">
                        </div>
                        <div id="register-link" class="text-right">
                            <input type="button" class="btn btn-primary btn-md" value="Зарегистрироваться"
                                   onclick="window.location.href ='${regButton}'"/>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

