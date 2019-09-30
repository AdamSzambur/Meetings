$(function () {
    $('div.card-footer').each(function (){

        if ($(this).data('error')===0) {
            $(this).hide();
        } else {
            $(this).show();
            $(this).prev().find('a.btn.btn-primary').hide();
        }
    });

    $('a.btn.btn-primary').on('click', function (even) {
        even.preventDefault();
        ($(this).parent().parent().parent().next()).show();
        console.log('dziala czy nie');
        console.log($(this).parent().parent().parent().next());
        $(this).hide();
    })
});