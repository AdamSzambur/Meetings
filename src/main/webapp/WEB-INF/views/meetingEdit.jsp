<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <div class="container-fluid p-3" style="background-color: #ff8600">
        <br><br><br>
        <span class="align-text-bottom"><h4><i class="far fa-calendar-plus"></i> Edycja spotkania</h4></span>
    </div>

    <div class="container">
        <div class="row justify-content-sm-center">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 rounded border p-5 whiteBg">
                <form:form method="post" modelAttribute="meeting" cssClass="form-group" enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <form:hidden path="ownerId" value="${user.id}"/>
                    <div class="form-group">
                        <label for="title">Tytuł</label>
                        <form:input path="title" cssClass="form-control" id="title" placeholder="Tytuł wydarzenia"/>
                        <form:errors path="title" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="description">Opis spotkania</label>
                        <form:textarea path="description" rows="5" cssClass="form-control" placeholder="Opis wydarzenia/spotkania"
                                       id="description"/>
                        <form:errors path="description" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="address">Miejsce spotkania</label>
                        <form:input path="address" cssClass="form-control" id="address" placeholder="Miejsce spotkania"/>
                        <form:errors path="address" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="meetTime">Data i godzina spotkania</label>
                        <form:input path="meetTime" cssClass="form-control col-sm-6" id="meetTime" type="datetime-local" value="${actualTime}" />
                        <form:errors path="meetTime" cssClass="error" element="div" />
                    </div>
                    <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                </form:form>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>

