<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/countChatMessages.js"></script>


<main role="main" class="flex-shrink-0">
    <br>
    <div class="container-fluid p-3" style="background-color: darkolivegreen">
        <br><br>
        <div class="row justify-content align-items-center" >
            <div class="col-sm-auto align-middle">
                <h4  style="color: white">Lista wszystkich wydarzeń/spotkań użytkownika</h4>
                <h6  style="color: white">Dodaj nowe wydarzenia, edytuj aktalnie dodane oraz przeglądaj te w ktorych bierzesz udział</h6>
            </div>
        </div>
    </div>
    <div class="container-fluid p-3 justify-content align-items-center" style="background-color:#6691CC;">
        <span mb-2 mr-sm-2 >Nie znalazłeś interesującego cię wydarzenia. &nbsp;&nbsp;</span>
        <button type="button" class="btn btn-warning" onclick="location.href='${mainURL}meetings/add'"><i class="far fa-calendar-plus"></i> Dodaj nowe</button>
    </div>

    <div class="container">
        <form method="get" action="/jee-crm-1.0-SNAPSHOT/order/orderList">
            <div class="input-group mb-3" >
                <input name="filter" id="filter" type="text" class="form-control"  placeholder="Podaj szukaną frazę w tablicy spotkań" aria-label="Podaj szukaną frazę w tablicy spotkań" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-danger" type="button" id="button-addon2" onclick="$('#filter').attr('value',''); $(this).closest('form').submit()">Wyczyść filtr</button>
                </div>
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" id="button-addon3" onclick="$(this).closest('form').submit()">Szukaj</button>
                </div>
            </div>
            <small id="emailHelp" class="form-text text-muted">Podaj szykaną frazę w tablicy spotkań.</small>
        </form>

        <br>


        <h5>Moje wydarzenia</h5>
        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Data i godzina</th>
                <th scope="col">Tytuł</th>
                <th scope="col">Miejsce spotkania</th>
                <th scope="col">Pozostałe info</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ownerMeetings}" var="meeting">
            <tr>
                <th scope="row">${meeting.meetTime.format(formater)}</th>
                <td>${meeting.title}</td>
                <td>${meeting.address}</td>
                <td>
                    <i class="fas fa-users" title="Członkowie"></i> ${(meeting.members.size()+1)},
<%--                    Utworzył : <img src="data:image/jpeg;base64,${meeting.owner.base64Image}" width="17" height="17" class="avatar"/> ${meeting.owner.fullName},--%>
                    <i class="far fa-comment-dots"  title="Liczba wiadomości na czacie"></i> <span data-meetingid ="${meeting.id}" class="chat_counter_homepage">[0]</span>,
                    <i class="far fa-comments" title="Liczba komentarzy"></i> ${meeting.commentsNumber}
                </td>
                <td>
                    <div class="btn-group" role="group" aria-label="First group">
                        <button type="button" class="btn btn-primary" onclick="window.location.href='/jee-crm-1.0-SNAPSHOT/order/orderEdit?orderId=4'">Edytuj</button>
                        <button type="button" class="btn btn-danger"  onclick="event.preventDefault();$('#deleteBtn').attr('href','/jee-crm-1.0-SNAPSHOT/order/orderDel?orderId=4'); $('#deleteMsg').toggleClass('invisible');">Usuń</button>
                    </div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <h5>Wydarzenia w ktorych biorę udział</h5>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">First</th>
                <th scope="col">Last</th>
                <th scope="col">Handle</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>

    </div>


</main>
<jsp:include page="footer.jsp"/>

