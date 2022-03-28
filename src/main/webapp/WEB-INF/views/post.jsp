<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>

    <title>Topic</title>
</head>
<body>
<c:url var="indexRef" value="/index">
</c:url>
<c:url var="editRef" value="/edit">
    <c:param name="userName" value="${userName}"/>
</c:url>
<c:url var="repRef" value="/reply">
    <c:param name="postId" value="${post.id}"/>
    <c:param name="userName" value="${userName}"/>
</c:url>

<c:url var="updateButton" value="/update">
    <c:param name="postId" value="${post.id}"/>
    <c:param name="userName" value="${userName}"/>
</c:url>
<c:url var="deleteButton" value="/delete">
    <c:param name="acId" value="${post.id}"/>
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
                    <a class="nav-link" aria-current="page" href="${indexRef}">Все темы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${editRef}">Создать новый пост</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active">Выбранная тема</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${logoutRef}">${userName} Выйти</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<form:hidden path="id"/>
<div class="container-fluid mt-100">
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <div class="card-header">

                    <div class="media flex-wrap w-100 align-items-center"><img
                            src="https://avotar.ru/avatar/prikolnye/100/12.jpg"
                            class="d-block ui-w-40 rounded-circle" alt="">

                        <div class="media-body ml-3"><a href="javascript:void(0)"
                                                        data-abc="true">${post.user.username}</a>
                            <div class="text-muted small"><fmt:formatDate value="${post.created.time}"
                                                                          pattern="yyyy-MM-dd HH:mm"/></div>
                        </div>
                    </div>
                </div>

                <div class="card-body">
                    <p> <h5>${post.name}</h5> </p>
                    <p> ${post.description} </p>
                    <div class="px-4 pt-3 text-right">
                        <c:if test="${post.user.username == userName}">
                            <button type="button" class="btn btn-danger" style="display: block; margin-left: auto;"
                                    onclick="window.location.href ='${deleteButton}'"><i
                                    class="ion ion-md-create"></i>&nbsp; Удалить
                            </button>
                        </c:if>
                    </div>
                </div>
                <div class="card-footer d-flex flex-wrap justify-content-between align-items-center px-0 pt-0 pb-3">
                    <div class="px-4 pt-3">
                        <button type="button" class="btn btn-primary"
                                onclick="window.location.href ='${repRef}'">
                            <i class="ion ion-md-create"></i>&nbsp; Ответить
                        </button>
                    </div>
                    <div class="px-4 pt-3 text-right">
                        <c:if test="${post.user.username == userName}">
                            <button type="button" class="btn btn-primary" style="display: block; margin-left: auto;"
                                    onclick="window.location.href ='${updateButton}'">
                                <i class="ion ion-md-create"></i>&nbsp;
                                Отредактировать
                            </button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <c:forEach var="reply" items="${post.replies}">
        <div class="card mb-4">
            <div class="card-header">

                <div class="media flex-wrap w-100 align-items-center"><img
                        src="https://avotar.ru/avatar/prikolnye/100/12.jpg"
                        class="d-block ui-w-40 rounded-circle" alt="">

                    <div class="media-body ml-3"><a href="javascript:void(0)"
                                                    data-abc="true">${reply.user.username}</a>
                        <div class="text-muted small"><fmt:formatDate value="${reply.date.time}"
                                                                      pattern="yyyy-MM-dd HH:mm"/></div>
                    </div>
                </div>
            </div>
            <c:url var="deleteReplyBut" value="/repdelete">
                <c:param name="postId" value="${post.id}"/>
                <c:param name="repId" value="${reply.id}"/>
                <c:param name="userName" value="${userName}"/>
            </c:url>

            <div class="card-body">
                <p> ${reply.text} </p>
            </div>
            <div class="card-footer d-flex flex-wrap justify-content-between align-items-center px-0 pt-0 pb-3">
                <div class="px-4 pt-3">
                </div>
                <div class="px-4 pt-3 text-right">
                    <button type="button" class="btn btn-danger" style="display: block; margin-left: auto;"
                            onclick="window.location.href ='${deleteReplyBut}'"><i
                            class="ion ion-md-create"></i>&nbsp; Удалить
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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