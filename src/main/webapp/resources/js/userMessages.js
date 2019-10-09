$(function () {
    var context = window.location.pathname;
    console.log(context);
    var messageBox = $('#messageBox');

    $('.remove').click(function () {
        messageBox
            .find('.messageValue').html('Czy napewno chcesz usunąć wiadomość<br><strong>'+ $(this).data('message') +'</strong> ?');
        messageBox.find('form').find('#messageId').attr('value',$(this).data('messageid'));
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