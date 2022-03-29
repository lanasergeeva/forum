<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Add reply</title>
</head>
<body>

<c:url var="indexRef" value="/index">
</c:url>
<c:url var="editRef" value="/edit">

</c:url>
<c:url var="logoutRef" value="/logout">
</c:url>

<c:url var="updateButton" value="/update">
    <c:param name="postId" value="${post.id}"/>

</c:url>

<nav class="navbar navbar-expand-lg navbar-light bg-light rounded" aria-label="Twelfth navbar example">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample10"
                aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${indexRef}">Все темы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${editRef}">Создать новый пост</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active">Новый комментарий</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${logoutRef}">${user.username} Выйти</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class='border border-dark p-4 rounded' id='basicInfo'>
        <form:form action="savereply" modelAttribute="reply">
            <input type="hidden" name="postId" value="${post.id}">

            <div class='form-group'>
                <label for='samsDesc' class='text-primary'>Описание</label>
                <form:textarea path="text" class="form-control" id='samsDesc' autocomplete="off"
                               placeholder="Описание"
                               rows="4"/>
                <div class='text-danger collapse' id='valDesc'>Описание темы.</div>
            </div>

            <div id="register-link" class="text-left">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>