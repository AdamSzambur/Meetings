<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <div class="container-fluid p-3" style="background-color: #ff8600">
        <br><br><br>
        <span class="align-text-bottom"><h4><i class="far fa-paper-plane"></i> Podgląd wiadomości</h4></span>
    </div>

    <div class="container">
        <div class="row justify-content-sm-center">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 rounded border p-5 whiteBg">

                    <div class="form-group">
                        <label for="sender">Nadawca</label>
                        <input type="text" value="${messageDTO.sender.fullName}" class="form-control col-sm-6" disabled id="sender">
                    </div>

                    <div class="form-group">
                        <label for="recipient">Odbiorca</label>
                        <input type="text" value="${messageDTO.recipient.fullName}" class="form-control col-sm-6" disabled id="recipient">
                    </div>

                    <div class="form-group">
                        <label for="title">Tytuł</label>
                        <input type="text" value="${messageDTO.title}" class="form-control" disabled id="title">
                    </div>
                    <div class="form-group">
                        <label for="text">Treść wiadomości</label>
                        <textarea rows="5" disabled class="form-control" id="text">${messageDTO.text}</textarea>
                    </div>
                    <c:if test="${param.box.equals('inbox')}">
                    <button type="submit" class="btn btn-primary" onclick="window.location.href=${mainURL}+'user/messages/add/response?messageId='+${messageDTO.id}"><i class="far fa-share-square"></i> Odpowiedz na wiadomość</button>
                    </c:if>

            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>

