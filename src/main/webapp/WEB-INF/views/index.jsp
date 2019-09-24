<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/chat.js"></script>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">

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



        Lista zalogowanych użytkowników :
        <c:forEach items="${loggedUsers}" var="loggedUser">
            <img src="data:image/jpeg;base64,${loggedUser.base64Image}" width="27" height="27" class="avatar"/>
             ${loggedUser.fullName}
        </c:forEach>
        <br><br>

        <!-- Button trigger modal -->
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


    </div>
    <br>



</main>

<jsp:include page="footer.jsp"/>

