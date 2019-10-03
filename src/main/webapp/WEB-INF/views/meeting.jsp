<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/chat.js"></script>
<script src="${mainURL}resources/js/comment.js"></script>
<script src="${mainURL}resources/js/meeting.js"></script>
<main role="main" class="flex-shrink-0">
    <div class="container-fluid p-3" style="background-color: #ff8600">
        <br><br><br>
        <span class="align-text-bottom"><h4><i class="fas fa-handshake"></i> Szczegóły spotkania ${meeting.title}</h4></span>
    </div>
    <div class="container">
        <div class="rounded border p-2">
            <div class="card mb-3">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <div id="map"></div>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${meeting.title}</h5>
                            <p class="card-text">${meeting.description}</p>
                            <p class="card-text"><small class="text-muted">
                                Ostatnia aktualizacja : ${meeting.updated.format(formater)}
                                </small></p>

                                <button type="button" class="btn btn-primary m-1" data-toggle="modal" data-target="#exampleModalScrollable"
                                        id="chat-button" data-current_meeting="${meeting.id}">
                                    Chat <i class="far fa-comment-dots"></i> <span class="" id="chat_counter">[0]</span>
                                </button>
                                <a href="#" class="btn btn-primary m-1"><i class="far fa-comments"></i> Dodaj komentarz</a>


                            <c:if test="${meeting.owner != user}">
                                <c:if test="${!meeting.members.contains(user)}">
                                <button id="addMember" type="button" class="btn btn-warning m-1"><i class="fas fa-user-plus"></i> Dołącz do wydarzenia</button>
                                </c:if>
                                <c:if test="${meeting.members.contains(user)}">
                                <button id="removeMember" type="button" class="btn btn-danger m-1"><i class="fas fa-user-minus"></i> Opuść wydarzenie</button>
                                </c:if>
                            </c:if>

                        </div>
                    </div>
                </div>
                <div class="card-footer" data-error="<c:out value="${error == 0 ? 1 : 0}"/>">
                    <form:form modelAttribute="newComment">
                        <form:hidden path="meetingId"/>
                        <div class="input-group mb-3">
                            <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit">
                                    <i class="fas fa-comment-plus"></i> Dodaj</button>
                            </div>
                        </div>
                        <form:errors path="text" cssClass="error" element="div" />
                    </form:form>
                </div>
            </div>

            <table class="table">
                <tr>
                    <td colspan="6">Komentarze</td>
                </tr>

            <c:forEach var="comment" items="${meeting.comments}">
                <tr>
                    <td colspan="6">
                        <div class="card">
                            <div class="card-header">
                                <img src="data:image/jpeg;base64,${comment.user.base64Image}" width="27" height="27" class="avatar"/>
                                <c:if test="${comment.user.email.equals(principal.email)}">
                                    ${comment.user.fullName}
                                </c:if>
                                <c:if test="${!comment.user.email.equals(principal.email)}">
                                    <a href="${mainURL}message/add?id=${comment.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${comment.user.fullName}</a>
                                </c:if>
                                , Utworzono : ${comment.created.format(formater)}
                            </div>
                            <div>
                                <div>
                                    <div class="card-body">
                                        <p class="card-text">${comment.text}</p>
                                        <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer" data-error="<c:out value="${error == comment.id ? 1 : 0}"/>">
                                <form:form modelAttribute="newComment" method="post">
                                    <form:hidden path="meetingId"/>
                                    <form:hidden path="parentId" value="${comment.id}"/>
                                    <div class="input-group mb-3">
                                        <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="submit">
                                                <i class="fas fa-comment-plus"></i> Dodaj</button>
                                        </div>
                                    </div>
                                    <form:errors path="text" cssClass="error" element="div" />
                                </form:form>
                            </div>
                        </div>
                    </td>
                </tr>
                <c:forEach var="child1" items="${comment.children}">
                    <tr>
                        <td></td>
                        <td colspan="5">
                            <div class="card">
                                <div class="card-header">
                                    <img src="data:image/jpeg;base64,${child1.user.base64Image}" width="27" height="27" class="avatar"/>
                                    <c:if test="${child1.user.email.equals(principal.email)}">
                                        ${child1.user.fullName}
                                    </c:if>
                                    <c:if test="${!child1.user.email.equals(principal.email)}">
                                        <a href="${mainURL}message/add?id=${child1.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child1.user.fullName}</a>
                                    </c:if>
                                    , Utworzono : ${child1.created.format(formater)}
                                </div>
                                <div>
                                    <div>
                                        <div class="card-body">
                                            <p class="card-text">${child1.text}</p>
                                            <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer" data-error="<c:out value="${child1.id == null ? 1 : 0}"/>">
                                    <form:form modelAttribute="newComment" method="post">
                                        <form:hidden path="meetingId"/>
                                        <form:hidden path="parentId" value="${child1.id}"/>
                                        <div class="input-group mb-3">
                                            <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-secondary" type="submit">
                                                    <i class="fas fa-comment-plus"></i> Dodaj</button>
                                            </div>
                                        </div>
                                        <form:errors path="text" cssClass="error" element="div" />
                                    </form:form>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:forEach var="child2" items="${child1.children}">
                        <tr>
                            <td></td>
                            <td></td>
                            <td colspan="4">
                                <div class="card">
                                    <div class="card-header">
                                        <img src="data:image/jpeg;base64,${child1.user.base64Image}" width="27" height="27" class="avatar"/>
                                        <c:if test="${child2.user.email.equals(principal.email)}">
                                            ${child1.user.fullName}
                                        </c:if>
                                        <c:if test="${!child2.user.email.equals(principal.email)}">
                                            <a href="${mainURL}message/add?id=${child2.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child2.user.fullName}</a>
                                        </c:if>
                                        , Utworzono : ${child2.created.format(formater)}
                                    </div>
                                    <div>
                                        <div>
                                            <div class="card-body">
                                                <p class="card-text">${child2.text}</p>
                                                <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer" data-error="<c:out value="${child2.id == null ? 1 : 0}"/>">
                                        <form:form modelAttribute="newComment" method="post">
                                            <form:hidden path="meetingId"/>
                                            <form:hidden path="parentId" value="${child2.id}"/>
                                            <div class="input-group mb-3">
                                                <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/>
                                                <div class="input-group-append">
                                                    <button class="btn btn-outline-secondary" type="submit">
                                                        <i class="fas fa-comment-plus"></i> Dodaj</button>
                                                </div>
                                            </div>
                                            <form:errors path="text" cssClass="error" element="div" />
                                        </form:form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <c:forEach var="child3" items="${child2.children}">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td colspan="3">
                                    <div class="card">
                                        <div class="card-header">
                                            <img src="data:image/jpeg;base64,${child3.user.base64Image}" width="27" height="27" class="avatar"/>
                                            <c:if test="${child3.user.email.equals(principal.email)}">
                                                ${child3.user.fullName}
                                            </c:if>
                                            <c:if test="${!child3.user.email.equals(principal.email)}">
                                                <a href="${mainURL}message/add?id=${child3.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child3.user.fullName}</a>
                                            </c:if>
                                            , Utworzono : ${child3.created.format(formater)}
                                        </div>
                                        <div>
                                            <div>
                                                <div class="card-body">
                                                    <p class="card-text">${child3.text}</p>
                                                    <a href="#" class="btn btn-primary"><i class="far fa-comments"></i> Dodaj komentarz</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer" data-error="<c:out value="${child3 == null ? 1 : 0}"/>">
                                            <form:form modelAttribute="newComment" method="post">
                                                <form:hidden path="meetingId"/>
                                                <form:hidden path="parentId" value="${child3.id}"/>
                                                <div class="input-group mb-3">
                                                    <form:input path="text" cssClass="form-control" placeholder="Nowy komentarz"/><br>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-outline-secondary" type="submit">
                                                            <i class="fas fa-comment-plus"></i> Dodaj</button>
                                                    </div>
                                                </div>
                                                <form:errors path="text" cssClass="error" element="div" />
                                            </form:form>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <c:forEach var="child4" items="${child3.children}">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td colspan="2">
                                        <div class="card">
                                            <div class="card-header">
                                                <img src="data:image/jpeg;base64,${child4.user.base64Image}" width="27" height="27" class="avatar"/>
                                                <c:if test="${child4.user.email.equals(principal.email)}">
                                                    ${child4.user.fullName}
                                                </c:if>
                                                <c:if test="${!child4.user.email.equals(principal.email)}">
                                                    <a href="${mainURL}message/add?id=${child4.user.id}" title="Wyślij wiadomość"><i class="far fa-envelope"></i> ${child4.user.fullName}</a>
                                                </c:if>
                                                , Utworzono : ${child4.created.format(formater)}
                                            </div>
                                            <div>
                                                <div>
                                                    <div class="card-body">
                                                        <p class="card-text">${child4.text}</p>
                                                    </div>
                                                </div>
                                            </div>
                                            </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>

                </c:forEach>

            </c:forEach>
            </table>



            <mapa>
                <script>
                    function initMap() {
                        function codeAddress(geocoder, address) {
                            geocoder.geocode({
                                    'address': address
                                }, // change
                                function(results, status) {
                                    if (status == 'OK') {
                                        var infowindow = new google.maps.InfoWindow({
                                            content: '${meeting.title}'
                                        });
                                        var map = new google.maps.Map(
                                            document.getElementById('map'), {zoom: 15, center: results[0].geometry.location});
                                        var marker = new google.maps.Marker({
                                            position: results[0].geometry.location,
                                            title : '${meeting.title}',
                                            map: map,
                                        });
                                        marker.addListener('click', function() {
                                            infowindow.open(map, marker);
                                        });

                                    } else {
                                        console.log('This didnt work' + status);
                                    }
                                });
                        };
                        codeAddress(new google.maps.Geocoder(), "${meeting.address}");
                    }
                </script>
                <script async defer
                        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC5EJjfoZUTXckzVuwbvm3Ke0SWYwoi6OI&callback=initMap">
                </script>
            </mapa>

            <chat>
                <!-- Modal -->
                <div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalScrollableTitle">Czat</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <form id="chatForm">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="meetingId" value="${meeting.id}">
                                    <div class="input-group mb-3">
                                        <input type="text" name="message" class="form-control" placeholder="Nowa wiadomość"
                                               id="message" aria-describedby="button-add">
                                        <div class="input-group-append">
                                            <input type="submit" class="btn btn-outline-secondary" id="button-add">
                                        </div>
                                    </div>
                                </form>

                                <br>

                                <table class="table" id="chat_table">
                                    <tr class="row">
                                        <td class="col-1"><i class="fas fa-handshake"></i>
                                        </td>
                                        <td class="col-11 text-left">
                                            Witamy na czacie naszej grupy.
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </chat>

            <message>
                <div class="alert modal_alert invisible" id="messageBox">
                    <button type="button" class="close" onclick="event.preventDefault(); $('#messageBox').toggleClass('invisible');">
                        <span>&times;</span>
                    </button><br>
                    <p style="text-align: center">
                        <span class="messageValue">Czy napewno chcesz usunąć tego pracownika</span>
                        <form:form modelAttribute="memberDTO" action="${mainURL}meetings/member">
                            <form:hidden path="meetingId" value="${meeting.id}"/>
                            <form:hidden path="userId" value="${user.id}"/>
                            <p style="text-align: center">
                                <button type="submit" role="button" class="btn btn-success">success</button>
                                <a role="button" class="btn btn-danger" href="" onclick="event.preventDefault(); $('#messageBox').toggleClass('invisible');">Anuluj</a>
                            </p>
                        </form:form>
                    </p>
                </div>
            </message>

        </div>
    </div>
    <br>
</main>
<jsp:include page="footer.jsp"/>

