<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <p class="container">
        <div class="form-row">
            <div class="form-group col-md-8">
                <h4 class="cover-heading">Lista wszystkich tweet'ów </h4>
            </div>
        </div>
        <p>
            <img src="data:image/jpeg;base64,${principal.base64Image}" width="40" height="40"/>

            <p>
            <h3>Zmienna globalna dostępna dla wszystkich uzytkowników aplikacji : ${varible}</h3>
            </p>


    <div id="map"></div>
    <script>
        // Note: This example requires that you consent to location sharing when
        // prompted by your browser. If you see the error "The Geolocation service
        // failed.", it means you probably did not give permission for the browser to
        // locate you.
        var map, infoWindow, cityCircle;
        var zoomLevel = 11;

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: -34.397, lng: 150.644},
                zoom: 6
            });



            infoWindow = new google.maps.InfoWindow;

            // Try HTML5 geolocation.
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    var pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    infoWindow.setPosition(pos);
                    infoWindow.setContent('Location found.');
                    infoWindow.open(map);
                    map.setCenter(pos);
                    map.setZoom(zoomLevel);
                }, function() {
                    handleLocationError(true, infoWindow, map.getCenter());
                });
            } else {
                // Browser doesn't support Geolocation
                handleLocationError(false, infoWindow, map.getCenter());
            }

            cityCircle = new google.maps.Circle({
                strokeColor: '#FF0000',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.35,
                map: map,
                center: map.getCenter(),
                radius: 100
            });



        }


        var circle = function () {
            cityCircle.setVisible(true);
        };


        var test = 100;








        function handleLocationError(browserHasGeolocation, infoWindow, pos) {
            infoWindow.setPosition(pos);
            infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
            infoWindow.open(map);
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC5EJjfoZUTXckzVuwbvm3Ke0SWYwoi6OI&callback=initMap">
    </script>



        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

