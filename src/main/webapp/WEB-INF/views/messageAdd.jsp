<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <div class="container-fluid p-3" style="background-color: #ff8600">
        <br><br><br>
        <span class="align-text-bottom"><h4><i class="far fa-paper-plane"></i> Wysyłanie nowej wiadomości</h4></span>
    </div>

    <div class="container">
        <div class="row justify-content-sm-center">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 rounded border p-5 whiteBg">
                <form:form method="post" modelAttribute="messageDTO" cssClass="form-group" action="${mainURL}user/messages/add">
                    <form:hidden path="sender" value="${messageDTO.sender.id}" />
                    <div class="form-group">
                        <label for="title">Odbiorca</label>
                        <form:select path="recipient" cssClass="form-control col-sm-6"  items="${recipients}" itemValue="id" itemLabel="fullName"/>
                        <form:errors path="recipient" cssClass="error" element="div" />
                    </div>

                    <div class="form-group">
                        <label for="title">Tytuł</label>
                        <form:input path="title" cssClass="form-control" id="title" placeholder="Tytuł wiadomości"/>
                        <form:errors path="title" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="text">Treść wiadomości</label>
                        <form:textarea path="text" rows="5" cssClass="form-control" placeholder="Treść wiadomości"
                                       id="text"/>
                        <form:errors path="text" cssClass="error" element="div" />
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="far fa-paper-plane"></i> Wyślij wiadomość</button>
                </form:form>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>

