<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/chat.js"></script>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
            <div class="card mb-3">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <div id="map"></div>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${meeting.title}</h5>
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>

                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalScrollable"
                                    id="chat-button" data-current_meeting="${meeting.id}">
                                Chat <i class="far fa-comment-dots"></i> <span class="" id="chat_counter">[0]</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

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
        </div>
    </div>
    <br>
</main>
<jsp:include page="footer.jsp"/>

