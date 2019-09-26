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
                <form class="form-inline">
                    <div class="input-group mb-2 mr-sm-2">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                        <input style="font-weight: bold" type="text" class="form-control" placeholder="Znajdź">
                    </div>

                    <span class="mb-2 mr-sm-2" style="font-weight: bold;"> &nbsp;&nbsp;w odległości&nbsp;</span>

                    <select class="custom-select mb-2 mr-sm-2" style="background-color: #ff8600; font-weight: bold; border: none">
                        <option value="20">20 km</option>
                        <option value="50">50 km</option>
                        <option value="40000">dużej :)</option>
                    </select>
                </form>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-9">
<%--                lista wydarzeń--%>

                <h4>Wydarzenia - następne 7 dni</h4><br>


                <c:forEach items="${meetingsNext7Days}" var="meeting">





                    <div class="card">
                        <div class="card-header">

                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Special title treatment</h5>
                            <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>






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


                <div class="row no-gutters bg-light position-relative">
                    <div class="col-md-6 mb-md-0 p-md-4">
                        <img src="..." class="w-100" alt="...">
                    </div>
                    <div class="col-md-6 position-static p-4 pl-md-0">
                        <h5 class="mt-0">Columns with stretched link</h5>
                        <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.</p>
                        <a href="#" class="stretched-link">Go somewhere</a>
                    </div>
                </div>


                <br>
                <br>
    <div class="row no-gutters bg-light position-relative">
        <div class="col-md-6 mb-md-0 p-md-4">
            <img src="..." class="w-100" alt="...">
        </div>
        <div class="col-md-6 position-static p-4 pl-md-0">
            <h5 class="mt-0">Columns with stretched link</h5>
            <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.</p>
            <a href="#" class="stretched-link">Go somewhere</a>
        </div>
    </div>

    <br>
    <br>
    <br>

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

        <%--            <img src="data:image/jpeg;base64,${principal.base64Image}" width="40" height="40"/>--%>

        <%--    <div id="map"></div>--%>
        <%--    <script>--%>
        <%--        // Note: This example requires that you consent to location sharing when--%>
        <%--        // prompted by your browser. If you see the error "The Geolocation service--%>
        <%--        // failed.", it means you probably did not give permission for the browser to--%>
        <%--        // locate you.--%>
        <%--        var map, infoWindow, cityCircle;--%>
        <%--        var zoomLevel = 11;--%>

        <%--        function initMap() {--%>
        <%--            map = new google.maps.Map(document.getElementById('map'), {--%>
        <%--                center: {lat: -34.397, lng: 150.644},--%>
        <%--                zoom: 6--%>
        <%--            });--%>



        <%--            infoWindow = new google.maps.InfoWindow;--%>

        <%--            // Try HTML5 geolocation.--%>
        <%--            if (navigator.geolocation) {--%>
        <%--                navigator.geolocation.getCurrentPosition(function(position) {--%>
        <%--                    var pos = {--%>
        <%--                        lat: position.coords.latitude,--%>
        <%--                        lng: position.coords.longitude--%>
        <%--                    };--%>

        <%--                    infoWindow.setPosition(pos);--%>
        <%--                    infoWindow.setContent('Location found.');--%>
        <%--                    infoWindow.open(map);--%>
        <%--                    map.setCenter(pos);--%>
        <%--                    map.setZoom(zoomLevel);--%>
        <%--                }, function() {--%>
        <%--                    handleLocationError(true, infoWindow, map.getCenter());--%>
        <%--                });--%>
        <%--            } else {--%>
        <%--                // Browser doesn't support Geolocation--%>
        <%--                handleLocationError(false, infoWindow, map.getCenter());--%>
        <%--            }--%>

        <%--            var cityCircle = function(){new google.maps.Circle({--%>
        <%--                strokeColor: '#FF0000',--%>
        <%--                strokeOpacity: 0.8,--%>
        <%--                strokeWeight: 2,--%>
        <%--                fillColor: '#FF0000',--%>
        <%--                fillOpacity: 0.35,--%>
        <%--                map: map,--%>
        <%--                center: map.getCenter(),--%>
        <%--                radius: 100--%>
        <%--            })};--%>
        <%--        }--%>


        <%--        var test = 100;--%>

        <%--        function handleLocationError(browserHasGeolocation, infoWindow, pos) {--%>
        <%--            infoWindow.setPosition(pos);--%>
        <%--            infoWindow.setContent(browserHasGeolocation ?--%>
        <%--                'Error: The Geolocation service failed.' :--%>
        <%--                'Error: Your browser doesn\'t support geolocation.');--%>
        <%--            infoWindow.open(map);--%>
        <%--        }--%>
        <%--    </script>--%>
        <%--    <script async defer--%>
        <%--            src="https://maps.googleapis.com/maps/api/js?key=MY_KEY_VALUE&callback=initMap">--%>
        <%--    </script>--%>
        <br><br>








<%--        <div class="form-row">--%>
<%--            <div class="form-group col-md-8">--%>
<%--                <h4 class="cover-heading">Lista ostatnich poropozycji spotkań </h4>--%>
<%--            </div>--%>
<%--        </div>--%>


<%--        <table class="table table-striped">--%>
<%--            <tbody>--%>
<%--            <c:forEach items="${meetingsNext7Days}" var="meeting" varStatus="i">--%>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <div class="card">--%>
<%--                            <div class="card-header">--%>
<%--                                    ${meeting.title}--%>
<%--                            </div>--%>
<%--                            <div class="card-body">--%>
<%--                                <p class="card-text">${meeting.description}</p>--%>
<%--                                <a href="${mainURL}tweet?id=${meeting.id}" class="btn btn-primary"><i class="fas fa-search-plus"></i> Pokaż szczegóły</a>--%>
<%--                                <p class="card-text" style="text-align: right">Liczba komentarzy <i class="far fa-comments"></i> ${meeting.meetTime}</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>


    </div>
    <br>



</main>

<jsp:include page="footer.jsp"/>