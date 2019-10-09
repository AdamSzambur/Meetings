<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/userMessages.js"></script>
<script src="${mainURL}resources/js/notification.js"></script>


<main role="main" class="flex-shrink-0">
    <br>
    <div class="container-fluid p-3" style="background-color: darkolivegreen">
        <br><br>
        <div class="row justify-content align-items-center" >
            <div class="col-sm-auto align-middle">
                <h4  style="color: white">Lista wszystkich wiadomości użytkownika</h4>
                <h6  style="color: white">Wiadomości od/do innych użytkowników aplikacji</h6>
            </div>
        </div>
    </div>
    <div class="container my-1 py-1">
        <notifications data-userid="${user.id}"></notifications>
    </div>
    <div class="container">
        <div class="rounded border p-2 whiteBg">
            <div class="form-row align-middle">

                <div class="form-group col-md-6">
                    <form method="get" class="form-inline">
                        <span class="mb-1 mr-sm-2">Wiadomości</span>
                        <select name="box" onchange="this.form.submit()"
                                class="custom-select mb-1 mr-sm-2" style="background-color: #F9F9F9; border: 0px">
                            <option value="inbox" <c:if test="${param.box.equals('inbox')}">selected</c:if>>odebrane</option>
                            <option value="outbox" <c:if test="${param.box.equals('outbox')}">selected</c:if>>wysłane</option>
                        </select>
                        <span class="mb-1 mr-sm-2">przez uzytkownika</span>
                    </form>
                </div>

                <div class="form-group col-md-6">
                    <form method="get">
                        <div class="input-group mb-3" >
                            <input type="hidden" name="box" value="${param.box}">
                            <input name="searchFraze" id="filter" type="text" class="form-control"  placeholder="Podaj szukaną frazę w tablicy wiadomości" aria-label="Podaj szukaną frazę w tablicy wiadmości" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-danger" type="button" id="button-addon2" onclick="$('#filter').attr('value',''); $(this).closest('form').submit()">Wyczyść filtr</button>
                            </div>
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="button" id="button-addon3" onclick="$(this).closest('form').submit()">Szukaj</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>


            <form method="post" action="${mainURL}user/messages/delete">
                <input type="hidden" value="${param.box}" name="box">
                <button type="button" class="btn btn-primary btn-sm"  title="Nowa wiadomość" onclick="window.location.href='${mainURL}user/messages/add?senderId=${user.id}'"><i class="far fa-paper-plane"></i> Nowa wiadomość</button>
                <button type="button" class="btn btn-danger btn-sm"  title="Usuń zaznaczone" onclick="$('#messageBoxAll').toggleClass('invisible');"><i class="far fa-trash-alt"></i> Usuń zaznaczone</button>
                <br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col"><button type="button" class="btn btn-outline-secondary btn-sm" title="odwróc zaznaczenie" id="toggleSelectedBtn"><i class="fas fa-sync-alt"></i></button></th>
                    <th scope="col">Wysłano</th>
                    <th scope="col"><c:out value="${param.box.equals('inbox') ? 'Nadawca' : 'Odbiorca'}"/></th>
                    <th scope="col">Tytuł</th>
                    <th scope="col">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${messages}" var="message">
                    <tr">
                        <th scope="row" >
                            <input type="checkbox" name="selectedMessages" value="${message.id}" class="check-box" style="transform: scale(1.5);">
                        </th>
                        <th scope="row">${message.created.format(formater)}</th>
                        <td class="meetingTitle"><c:out value="${param.box.equals('inbox') ? message.sender.fullName : message.recipient.fullName}"/></td>
                        <td>${message.title}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" class="btn btn-success btn-sm" title="Pogląd wiadomości" onclick="window.location.href='${mainURL}user/messages/message?box=${param.box}&messageId=${message.id}'"><i class="far fa-eye"></i></button>
                                <button type="button" class="btn btn-danger remove btn-sm"  data-message="${message.title}" data-messageid="${message.id}" title="Usuń"><i class="far fa-trash-alt"></i></button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

                <message>
                    <div class="alert modal_alert invisible alert-danger" id="messageBoxAll">
                        <button type="button" class="close" onclick="event.preventDefault(); $('#messageBoxAll').toggleClass('invisible');">
                            <span>&times;</span>
                        </button><br>
                        <p style="text-align: center">
                            <span class="messageValue">Czy napewno chcesz usunąć zaznaczone wiadomości</span>
                                <input type="hidden" name="notificationId" value="">
                        <p style="text-align: center">
                            <button type="submit" role="button" class="btn btn-success">Usuń wiadomości</button>
                            <a role="button" class="btn btn-danger" href="" onclick="event.preventDefault(); $('#messageBoxAll').toggleClass('invisible');">Anuluj</a>
                        </p>

                    </p>
                </div>
                </message>

            </form>
        </div>
    </div>
    <br>

    <message>
        <div class="alert modal_alert invisible alert-danger" id="messageBox">
            <button type="button" class="close" onclick="event.preventDefault(); $('#messageBox').toggleClass('invisible');">
                <span>&times;</span>
            </button><br>
            <p style="text-align: center">
                <span class="messageValue"></span>
                <form method="post" action="${mainURL}user/messages/delete">
                    <input type="hidden" value="${param.box}" name="box">
                    <input type="hidden" name="messageId" value="" id="messageId">
                    <p style="text-align: center">
                        <button type="submit" role="button" class="btn btn-success">Usuń wiadomość</button>
                        <a role="button" class="btn btn-danger" href="" onclick="event.preventDefault(); $('#messageBox').toggleClass('invisible');">Anuluj</a>
                    </p>
                </form>
            </p>
        </div>
    </message>
</main>
<jsp:include page="footer.jsp"/>

