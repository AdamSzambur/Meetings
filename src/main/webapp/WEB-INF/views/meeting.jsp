<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<script src="${mainURL}resources/js/chat.js"></script>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
        <h4 class="cover-heading">Kata spotkania o nazwie ${meeting.title}</h4>

            <chat>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalScrollable"
                        id="chat-button" data-current_meeting="${meeting.id}">
                    Chat <i class="far fa-comment-dots"></i> <span class="" id="chat_counter">[0]</span>
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalScrollableTitle">Czat</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <form id="chatForm">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="meetingId" value="${meeting.id}">
                                    <div class="input-group mb-3">
                                        <input type="text" name="message" class="form-control" placeholder="Nowa wiadomość"
                                               id="message" aria-describedby="button-add">
                                        <div class="input-group-append">
                                            <input type="submit" class="btn btn-outline-secondary" id="button-add">
                                        </div>
                                    </div>
                                </form>

                                <br>

                                <table class="table" id="chat_table">
                                    <tr class="row">
                                        <td class="col-1"><i class="fas fa-handshake"></i>
                                        </td>
                                        <td class="col-11 text-left">
                                            Witamy na czacie naszej grupy.
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </chat>

        </div>
    </div>
    <br>
</main>
<jsp:include page="footer.jsp"/>

