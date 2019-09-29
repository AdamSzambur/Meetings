$(function () {
    var context = window.location.pathname;

    function testAjax(meetingId, span) {
        $.ajax({
            url: context + "meetings/chat/length?id=" + meetingId
        }).done(function (result) {
            if (result>0) {
                span.addClass('chat_counter');
            } else {
                span.removeClass('chat_counter');
            }
            span.html('['+result+']');
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        });
    }

    function getLengthMessageTable() {

        $('.chat_counter_homepage').each(function(){
            var meetingId = $(this).data("meetingid");
            testAjax(meetingId, $(this));
        });
    }

    getLengthMessageTable();

    setInterval(getLengthMessageTable,5000);
});