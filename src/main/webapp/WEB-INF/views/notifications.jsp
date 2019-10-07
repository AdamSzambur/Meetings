<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/userNotifications.js"></script>
<script src="${mainURL}resources/js/notification.js"></script>


<main role="main" class="flex-shrink-0">
    <br>
    <div class="container-fluid p-3" style="background-color: darkolivegreen">
        <br><br>
        <div class="row justify-content align-items-center" >
            <div class="col-sm-auto align-middle">
                <h4  style="color: white">Lista wszystkich powiadomień użytkownika</h4>
                <h6  style="color: white">Usuń powiadomienie lub przejdź do konkretnej zakładki z elementem wydarzenia</h6>
            </div>
        </div>
    </div>
    <div class="container my-1 py-1">
        <notifications data-userid="${user.id}"></notifications>
    </div>
    <div class="container">
        <div class="rounded border p-2 whiteBg">



            <form method="post" action="${mainURL}user/notifications/delete">
            <button type="button" class="btn btn-danger btn-sm"  title="Usuń zaznaczone" onclick="$('#messageBoxAll').toggleClass('invisible');"><i class="far fa-trash-alt"></i> Usuń zaznaczone</button>
            <br><br>
            <table class="table table-hover">
                <thead class="thead-dark">
                <tr>
                    <th scope="col"><button type="button" class="btn btn-outline-secondary btn-sm" title="odwróc zaznaczenie" id="toggleSelectedBtn"><i class="fas fa-sync-alt"></i></button></th>
                    <th scope="col">Data i godz.</th>
                    <th scope="col">Tresc</th>
                    <th scope="col">Status</th>
                    <th scope="col">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${notifications}" var="notification">
                    <tr class="table-${notification.alertType}">
                        <th scope="row" >
                            <input type="checkbox" name="selectedNotifications" value="${notification.id}" class="check-box" style="transform: scale(1.5);">
                        </th>
                        <th scope="row">${notification.created.format(formater)}</th>
                        <td class="meetingTitle">${notification.text}</td>
                        <td>${notification.status}</td>
                        <td>
                            <button type="button" class="btn btn-danger remove btn-sm"  data-notification="${notification.created.format(formater)}" data-notificationid="${notification.id}" title="Usuń"><i class="far fa-trash-alt"></i></button>
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
                            <span class="messageValue">Czy napewno chcesz usunąć zaznaczone powiadomienia</span>
                                <input type="hidden" name="notificationId" value="">
                        <p style="text-align: center">
                            <button type="submit" role="button" class="btn btn-success">Usuń wydarzenia</button>
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
                <form method="post" action="${mainURL}user/notifications/delete">
                    <input type="hidden" name="notificationId" value="">
                    <p style="text-align: center">
                        <button type="submit" role="button" class="btn btn-success">Usuń wydarzenie</button>
                        <a role="button" class="btn btn-danger" href="" onclick="event.preventDefault(); $('#messageBox').toggleClass('invisible');">Anuluj</a>
                    </p>
                </form>
            </p>
        </div>
    </message>
</main>
<jsp:include page="footer.jsp"/>

