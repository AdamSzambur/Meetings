$(function () {

    var context = window.location.pathname;

    console.log(context);

    // zaciągmay wszystkie wiadomosci z aplikajci (pod warunkiem ze sa nowe)
    var currentMessages = [];

    function getAllMessages() {
        $.getJSON({
            url: context+'chat/',
            data: {},
        }, function(json) {

            json.reverse();
            // console.log("Getting all messages");
            // sprawdzamy czy pojawiła się jakaś nowa wiadomosc na serwerze. Sprawdzamy ostatni wpis.
            var torf = false;
            if (json.length >0) {
                if (currentMessages.length > 0) {
                    if (json[0].created.nano != currentMessages[0].created.nano) {
                        // console.log(json[0].created.nano);
                        // console.log(currentMessages[0].created.nano);
                        currentMessages = json;
                        torf = true;
                    }
                } else {
                    currentMessages = json;
                    torf = true;
                }
            }
            if (torf) {
                // console.log("You have new messages in chat " + currentMessages.length);
                refreshChat();
            }
        });
    }

    setInterval(getAllMessages,5000);



    //dodajemy odpowiednie zdarzenie na formie chatForm
    var chatForm = $('#chatForm');
    chatForm.on('submit',function(e){
        e.preventDefault();
        // zamiana tabeli na obiekt
        var formValuesTable = $(this).serializeArray();

        var result = {};
        for (var i=0; i<formValuesTable.length; i++) {
            result[formValuesTable[i].name] = formValuesTable[i].value;
        }
        result = JSON.stringify(result);

        var startOption = {
            url: context+"chat/",
            data: result,
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            async: true
        };
        $('#message').value ='';
        return $.ajax(startOption).done(function (result) {

        }).fail(function (xhr, status, err) {

        }).always(function (xhr, status) {
            $('#message').val('');
            getAllMessages();
        });
    });


    // tutaj jest funkcja ktora dipisuje brakujące



    function refreshChat() {
        var chatTable = $('#chat_table');

        chatTable.html('');

        for (element of currentMessages) {
            var row =$('<tr class="row"><td class="col-1"><img src="data:image/jpeg;base64,'+element.userAvatar+'" title="'+element.userName+'" width="27" height="27" class="avatar"/></td><td class="col-11 text-left">'+element.message+'</td></tr>');
            chatTable.append(row);

        }
    }






});