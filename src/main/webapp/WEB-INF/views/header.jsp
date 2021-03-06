<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/" var="mainURL"/>
<!doctype html>
<html lang="pl" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Meetings by Adam</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Simple calendar core -->
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${mainURL}resources/css/simple-calendar.css">
    <link rel="stylesheet" href="${mainURL}resources/css/demo.css">

    <script src="https://kit.fontawesome.com/da6d9e6874.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link rel="stylesheet" href="${mainURL}resources/css/header.css"/>
    <link rel="stylesheet" href="${mainURL}resources/css/chat.css"/>
</head>
<body class="d-flex flex-column h-100 backgroundImage">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${mainURL}">
            <i class="fas fa-handshake"></i> MEETINGS</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <c:if test="${principal!= null}">
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${mainURL}"><i class="fas fa-home"></i> Strona
                            główna</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="navbarDropdownMenuLink1" role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <img src="data:image/jpeg;base64,${principal.base64Image}" width="27" height="27" class="avatar"/>&nbsp;${principal.fullName}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
                            <a class="dropdown-item" href="${mainURL}user/meetings"><i class="fas fa-handshake"></i> Moje Spotkania</a>
                            <a class="dropdown-item" href="${mainURL}user/notifications"><i class="far fa-bell"></i> Powiadomienia</a>
                            <a class="dropdown-item" href="${mainURL}user/messages?box=inbox"><i class="fas fa-envelope-open-text"></i>
                                Wiadomości [<span style="color: red;">${numberOfNewMessages}</span>]</a>
                            <a class="dropdown-item" href="${mainURL}user/userEdit"><i class="fas fa-user-cog"></i> Moje ustawienia</a>
                        </div>
                    </li>
                </ul>
                <a class="nav-link" href="${mainURL}logout"><i class="fas fa-sign-out-alt"></i>&nbsp;Wyloguj się</a>
            </div>
        </c:if>
        <c:if test="${principal == null}">
<%--            Trochę masakra ale fajnie było przecwiczyć też trochę nowych funkcji --%>
            <c:set value="${fn:split(pageContext.request.requestURL, '/')}" var="splitUrl"/>
            <c:if test="${splitUrl[fn:length(splitUrl)-1].equals('login.jsp')}">
            <a class="nav-link" href="${mainURL}user/register"><i class="fas fa-user-plus"></i>
                Zarejestruj nowego użytkownika
            </a>
            </c:if>
            <c:if test="${splitUrl[fn:length(splitUrl)-1].equals('register.jsp')}">
            <a class="nav-link" href="${mainURL}user/login"><i class="fas fa-sign-in-alt"></i>
                Zaloguj się
            </a>
            </c:if>
        </c:if>

    </nav>
</header>
