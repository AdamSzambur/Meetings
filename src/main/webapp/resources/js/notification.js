$(function () {
    var context = "/"+(window.location.pathname).split("/")[1]+"/";

    var userId = $('notifications').data('userid');
    var notifiContainer = $('notifications');

    console.log(context);

    function getAllUnreadNotifications() {

        $.getJSON({
            url: context+'user/notification/'+userId+'/unread',
            data: {}
        }, function(json) {

            json.forEach(function(notification){

                if (!findNotification(notification.id)) {
                    var alert = $(
                        '<div class="alert alert-'+notification.alertType+' alert-dismissible fade show" role="alert" data-id="' + notification.id + '">' +
                        '<button type="button" class="btn btn-'+notification.alertType+'" title="Przejdź do elementu"><i class="far fa-hand-point-right"></i></button> ' +
                        '['+parseLocalDateTime(notification.created)+'] '+notification.text +
                        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                        '<span aria-hidden="true">&times;</span>' +
                        '</button>' +
                        '</div>'
                    );

                    alert.find('.btn').click(function () {
                        notificationReaded(notification.id, context + notification.url);
                    });

                    // dopisujemy zdarzenie podczas zamknięcia powiadomienia
                    alert.on('closed.bs.alert', function() {
                        notificationReaded(notification.id);
                    });

                    notifiContainer.append(alert);
                }
            });
        });
    }

    function findNotification(id) {
        var alerts = $('.alert');

        var result = false;

        alerts.each(function() {
            if ($(this).data('id') === id) {
                result = true;
            }
        });
        return result;
    }

    function notificationReaded(id, redirectUrl) {

        console.log(context+"user/notification/"+id+"/read");

        $.ajax({
            url: context+"user/notification/"+id+"/read",
            type: "PUT"
        }).done(function(result) {
            if (redirectUrl) {
                window.location.href = redirectUrl;
            }

            console.log('Status powiadomienia o indeksie '+id+' zmienioneo na read');
        }).fail(function(xhr,status,err) {
            console.log('Nie zmieniono statusu');
        }).always(function(xhr,status) {
        });

    }

    getAllUnreadNotifications();
    setInterval(getAllUnreadNotifications,5000);


    function parseLocalDateTime(time) {
        var result = time[0]
            +"-"+((time[1]>9)?time[1]:("0"+time[1]))
            +"-"+((time[2]>9)?time[2]:("0"+time[2]))
            +" "+((time[3]>9)?time[3]:("0"+time[3]))
            +":"+((time[4]>9)?time[4]:("0"+time[4]))
            +":"+((time[5]>9)?time[5]:("0"+time[5]));
        return result;
    }

});