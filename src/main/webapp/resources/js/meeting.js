$(function () {
    var context = window.location.pathname;
    console.log(context);
    var messageBox = $('#messageBox');

    $('#addMember').click(function () {
        messageBox
            .removeClass('alert-danger')
            .addClass('alert-warning')
            .find('.messageValue').html('Czy napewno chcesz dołączyć do wydarzenia ?');
        messageBox.find('.btn.btn-success').html('Dodaj mnie do grupy');
        // to jest doskonały przyklad jakim gównem jest JQuery i cały ten JS - oczywoście nie dziala.
        // messageBox.find('form')).attr('action',context+'/member/add');
        // zamias tego musimy uzyc JS
        //document.getElementById("memberDTO").setAttribute('action',context+'/member/add');
        // niestaty to tez nie dziala. Ale syf.
        // chociaz moze to jest kwestja bezpieczeństwa juz am nie wiem.
        messageBox.find('#action').attr('value','add');
        messageBox.toggleClass('invisible');
    });

    $('#removeMember').click(function () {
        messageBox
            .removeClass('alert-warning')
            .addClass('alert-danger')
            .find('.messageValue').html('Czy napewno chcesz opuścić wydarzenie ?');
        messageBox.find('.btn.btn-success').html('Usuń mnie z grupy');
        messageBox.find('#action').attr('value','delete');
        messageBox.toggleClass('invisible');
    })
});