$(function () {
    var context = window.location.pathname;
    console.log(context);
    var messageBox = $('#messageBox');

    $('.remove').click(function () {
        messageBox
            .find('.messageValue').html('Czy napewno chcesz usunąć wydarzenie<br>'+ $(this).data('meetingtitle') +' ?');
        messageBox.find('form').find('input').attr('value',$(this).data('meetingid'));
        messageBox.toggleClass('invisible');
    });
});