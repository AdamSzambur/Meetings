<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/jquery.simple-calendar.js"></script>
<script src="${mainURL}resources/js/countChatMessages.js"></script>

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
                    <form:select id="distance" path="distance" cssClass="custom-select mb-2 mr-sm-2" cssStyle="background-color: #ff8600; font-weight: bold; border: none">
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
        <button type="button" class="btn btn-warning" onclick="location.href='${mainURL}meetings/add'"><i class="far fa-calendar-plus"></i> Dodaj nowe</button>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <br><h4><b>${title}</b></h4><br>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <c:forEach items="${meetings}" var="meeting">
                    <div class="card">
                        <div class="card-header">
                            <b>${meeting.title}</b>
                        </div>
                        <div class="card-body" id="meeting${meeting.id}">
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="card">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><b>${changeEnglishToPolish.parseDayEnglishToPolish(meeting.meetTime.dayOfWeek)} ${meeting.meetTime.hour}:${meeting.meetTime.minute}</b></li>
                                            <li class="list-group-item">Data : <b>${meeting.meetTime.dayOfMonth} ${changeEnglishToPolish.parseMonthEnglishToPolish(meeting.meetTime.month.name())} ${meeting.meetTime.year}</b></li>
                                            <li class="list-group-item">Adres : <b>${meeting.address}</b></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-7">

                                    <div class="container">
                                        <div class="row align-content-start">
                                            <div class="col-12">
                                                <b>Opis : </b>
                                                <c:out value="${meeting.description.length()>200 ? meeting.description.substring(0,199).concat('...') : meeting.description}"/>
                                            </div>
                                        </div>
                                        <div class="row text-right">
                                            <div class="col-12">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <a href="${mainURL}meetings?id=${meeting.id}" class="stretched-link"></a>
                            </div>
                        </div>
                        <div class="card-footer">
                            <span style="font-size: small">
                                Członkowie : <i class="fas fa-users"></i> ${(meeting.members.size()+1)},
                                Utworzył : <img src="data:image/jpeg;base64,${meeting.owner.base64Image}" width="17" height="17" class="avatar"/> ${meeting.owner.fullName},
                                Chat <i class="far fa-comment-dots"></i> <span data-meetingid ="${meeting.id}" class="chat_counter_homepage">[0]</span>,
                                Komentarze <i class="far fa-comments"></i> ${meeting.commentsNumber}
                            </span>
                        </div>
                    </div>
                    <br>
                    <br>
                </c:forEach>
            </div>

            <div class="col-sm-4">
                <div class="rounded border p-2 whiteBg">
                    <div id="map"></div>
                </div>
            </div>


            <div class="col-sm-2">
                <div class="rounded border p-3 whiteBg">
                    <div id="container" class="calendar-container"></div>
                    <script>
                        $(document).ready(function(){
                            $("#container").simpleCalendar({
                                fixedStartDay: false
                            });
                        });
                    </script>
                    Zalogowani użytkownicy :<br>
                    <c:forEach items="${loggedUsers}" var="loggedUser">
                        <img src="data:image/jpeg;base64,${loggedUser.base64Image}" width="27" height="27" class="avatar"/>
                        ${loggedUser.fullName}<br>
                    </c:forEach>
                </div>

            </div>

        </div>


    </div>
    <br>

    <googlemap>
        <script>

            function initMap() {
                // Przepisujemy tablice z Javy do JS :)
                var meetingsJS = new Array();
                <c:forEach items="${meetings}" var="meeting" varStatus="i">
                meetingsJS[${i.index}] = new Array();
                meetingsJS[${i.index}]['id'] = '${meeting.id}';
                meetingsJS[${i.index}]['title'] = '${meeting.title}';
                meetingsJS[${i.index}]['latitude'] = '${meeting.latitude}';
                meetingsJS[${i.index}]['longitude'] = '${meeting.longitude}';
                </c:forEach>

                // pobieramy aktualny zakres obszaru ktorego dotyczy lista
                var distanceSelect = document.getElementById("distance");
                var distance = distanceSelect.options[distanceSelect.selectedIndex].value;

                // Try HTML5 geolocation.
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        $('#latitude').attr('value', position.coords.latitude);
                        $('#longitude').attr('value', position.coords.longitude);


                        map = new google.maps.Map(document.getElementById('map'), {
                            center: {lat: position.coords.latitude, lng: position.coords.longitude},
                            zoom: 8
                        });

                        meetingsJS.forEach(function(meeting) {

                            // var infowindow = new google.maps.InfoWindow({
                            //     content : meeting.title
                            // });

                            var myLatlng = new google.maps.LatLng(parseFloat(meeting.latitude),parseFloat(meeting.longitude));

                            var marker = new google.maps.Marker({
                                position: myLatlng,
                                title : meeting.title,
                                map: map,
                            });

                            marker.addListener('click', function() {
                                // infowindow.open(map, marker);
                                document.location.href=window.location.pathname+'meetings?id='+meeting.id;
                            });
                            marker.addListener('mouseover', function() {
                                $('#meeting'+meeting.id).addClass('googlehover');
                            });

                            marker.addListener('mouseout', function() {
                                $('#meeting'+meeting.id).removeClass('googlehover');
                            });

                        });
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

</main>

<jsp:include page="footer.jsp"/>