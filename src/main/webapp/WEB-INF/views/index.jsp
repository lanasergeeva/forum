<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Форум job4j</title>
</head>
<body>

<c:url var="indexRef" value="/index">
</c:url>

<c:url var="editRef" value="/edit">
</c:url>
<c:url var="logoutRef" value="/logout">
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
                    <a class="nav-link active" aria-current="page" href="${indexRef}">Все темы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${editRef}">Создать новый пост</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${logoutRef}">${user.username} Выйти</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Форум job4j</th>
                    <th>Дата поста</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="post" items="${posts}">
                    <c:url var="postButton" value="/post">
                        <c:param name="postId" value="${post.id}"/>
                    </c:url>
                    <tr>
                        <td><b>${post.user.username}</b><br/>
                                ${post.name}<br/><a href="${postButton}"
                                                    target="_blank">Перейти к теме.
                            </a><br/><i class="fa fa-map-marker"></i>
                        </td>
                        <td><fmt:formatDate value="${post.created.time}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>