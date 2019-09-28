<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/chat.js"></script>
<script src="${mainURL}resources/js/jquery.simple-calendar.js"></script>


<main role="main" class="flex-shrink-0">
    <br>
    <div class="container-fluid p-3" style="background-color: #ff8600">
        <br>
        <div class="row justify-content align-items-center" >
            <div class="col-sm-auto">
                <h4>Witamy na stronie głównej aplikacji</h4>
                <h6>Użyj wyszukiwarki żeby znaleźć interesujące cię wydarzenie</h6>
            </div>

            <div class="col-sm-auto align-middle">
                <form:form cssClass="form-inline" modelAttribute="finderFormDTO" method="get">
                    <form:hidden path="latitude" value="" id="latitude"/>
                    <form:hidden path="longitude" value="" id="longitude"/>
                    <div class="input-group mb-2 mr-sm-2">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                        <form:input path="findPhrase" cssStyle="font-weight: bold" cssClass="form-control" placeholder="Znajdź"/>
                    </div>
                    <span class="mb-2 mr-sm-2" style="font-weight: bold;"> &nbsp;&nbsp;w odległości&nbsp;</span>
                    <form:select path="distance" cssClass="custom-select mb-2 mr-sm-2" cssStyle="background-color: #ff8600; font-weight: bold; border: none">
                        <form:option value="5" label="5 km"/>
                        <form:option value="10" label="10 km"/>
                        <form:option value="20" label="20 km"/>
                        <form:option value="50" label="50 km"/>
                        <form:option value="90000" label="far away"/>
                    </form:select>
                        <button type="submit" class="btn btn-primary">Szukaj</button>
                </form:form>


            </div>
        </div>
    </div>
    <div class="container-fluid p-3 justify-content align-items-center" style="background-color:#6691CC;">
        <span mb-2 mr-sm-2 >Nie znalazłeś interesującego cię wydarzenia. &nbsp;&nbsp;</span>
        <button type="button" class="btn btn-warning" onclick="location.href='${mainURL}meeting/add'">Dodaj nowe</button>




<%--        <div class="row justify-content align-items-center" >--%>
<%--            <div class="col-sm-auto">--%>
<%--                <h4>Witamy na stronie głównej aplikacji</h4>--%>
<%--                <h6>Użyj wyszukiwarki żeby znaleźć interesujące cię wydarzenie</h6>--%>
<%--            </div>--%>

<%--            <div class="col-sm-auto align-middle">--%>
<%--                <form:form cssClass="form-inline" modelAttribute="finderFormDTO" method="get">--%>
<%--                    <form:hidden path="latitude" value="" id="latitude"/>--%>
<%--                    <form:hidden path="longitude" value="" id="longitude"/>--%>
<%--                    <div class="input-group mb-2 mr-sm-2">--%>
<%--                        <div class="input-group-prepend">--%>
<%--                            <span class="input-group-text"><i class="fas fa-search"></i></span>--%>
<%--                        </div>--%>
<%--                        <form:input path="findPhrase" cssStyle="font-weight: bold" cssClass="form-control" placeholder="Znajdź"/>--%>
<%--                    </div>--%>
<%--                    <span class="mb-2 mr-sm-2" style="font-weight: bold;"> &nbsp;&nbsp;w odległości&nbsp;</span>--%>
<%--                    <form:select path="distance" cssClass="custom-select mb-2 mr-sm-2" cssStyle="background-color: #ff8600; font-weight: bold; border: none">--%>
<%--                        <form:option value="5" label="5 km"/>--%>
<%--                        <form:option value="10" label="10 km"/>--%>
<%--                        <form:option value="20" label="20 km"/>--%>
<%--                        <form:option value="50" label="50 km"/>--%>
<%--                        <form:option value="90000" label="far away"/>--%>
<%--                    </form:select>--%>
<%--                    <button type="submit" class="btn btn-primary">Szukaj</button>--%>
<%--                </form:form>--%>


<%--            </div>--%>
<%--        </div>--%>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-9">
<%--                lista wydarzeń--%>

                <h4>${title}</h4><br>


                <c:forEach items="${meetings}" var="meeting">

<%--                    <div class="card">--%>
<%--                        <div class="card-header">--%>

<%--                        </div>--%>
<%--                        <div class="card-body">--%>
<%--                            <h5 class="card-title">Special title treatment</h5>--%>
<%--                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>--%>
<%--                            <a href="#" class="btn btn-primary">Go somewhere</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>

                    <div class="row no-gutters bg-light position-relative">
                            <div class="col-md-6 mb-md-0 p-md-4">
                                <h5>${meeting.meetTime.dayOfWeek.name()} ${meeting.meetTime.hour}:${meeting.meetTime.minute} </h5>
                                <h6>Data : ${meeting.meetTime.dayOfMonth} ${meeting.meetTime.month.name()} ${meeting.meetTime.year}</h6>
                                <span>${meeting.address}</span>
                            </div>
                            <div class="col-md-6 p-4 pl-md-0">
                                <h5 class="mt-0">${meeting.title}</h5>
                                <p>${meeting.description}</p>
                            </div>
                        <a href="#" class="stretched-link"></a>
                    </div>
                    <br>
                    <br>
                </c:forEach>
            </div>
            <div class="col-sm-3">
<%--                kalendarz i moze lista zalogowanych uzytkowników--%>
                <div id="container" class="calendar-container"></div>
                <script>
                    $(document).ready(function(){
                        $("#container").simpleCalendar({
                            fixedStartDay: false
                        });
                    });
                </script>


                <googlemap>
                    <script>
                        function initMap() {
                            // Try HTML5 geolocation.
                            if (navigator.geolocation) {
                                navigator.geolocation.getCurrentPosition(function(position) {
                                    $('#latitude').attr('value', position.coords.latitude);
                                    $('#longitude').attr('value', position.coords.longitude);
                                }, function() {
                                    // console.log(infoWindow);
                                });
                            } else {
                                 console.log("Browser doesn't support Geolocation");
                            }
                        }

                    </script>
                    <script async defer
                            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC5EJjfoZUTXckzVuwbvm3Ke0SWYwoi6OI&callback=initMap">
                    </script>
                    <br><br>
                </googlemap>


                Lista zalogowanych użytkowników :
                <c:forEach items="${loggedUsers}" var="loggedUser">
                    <img src="data:image/jpeg;base64,${loggedUser.base64Image}" width="27" height="27" class="avatar"/>
                    ${loggedUser.fullName}<br>
                </c:forEach>

                <br><br>
                <chat>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalScrollable">
                        Chat <i class="far fa-comment-dots"></i> <span class="" id="chat_counter">[0]</span>
                    </button>

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
                                        <input type="hidden" name="meetingId" value="1">
                                        <div class="input-group mb-3">
                                            <input type="text" name="message" class="form-control" placeholder="Nowa wiadomość"
                                                   id="message" aria-describedby="button-add">
                                            <div class="input-group-append">
                                                <input type="submit" class="btn btn-outline-secondary" id="button-add">
                                            </div>
                                        </div>
                                    </form><br>

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


            </div>




        </div>




    </div>
    <br>



</main>

<jsp:include page="footer.jsp"/>