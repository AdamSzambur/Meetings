$(function () {
    var context = window.location.pathname;
    console.log(context);
    var messageBox = $('#messageBox');

    $('.remove').click(function () {
        messageBox
            .find('.messageValue').html('Czy napewno chcesz usunąć powiadomienie<br>z dnia/godz. <strong>'+ $(this).data('notification') +'</strong> ?');
        messageBox.find('form').find('input').attr('value',$(this).data('notificationid'));
        messageBox.toggleClass('invisible');
    });

    $('#toggleSelectedBtn').click(function () {
        toggleSelected();
    })

    function toggleSelected() {
        var checkboxes = $('.check-box');
        checkboxes.each(function(){
            if ($(this).prop('checked')) {
                $(this).prop('checked',false);
            } else {
                $(this).prop('checked',true);
            }
        })
    }





});