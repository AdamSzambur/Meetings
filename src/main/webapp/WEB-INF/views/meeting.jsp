<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <div class="rounded border p-5">
        <h4 class="cover-heading">Dodaj nowe spotkanie</h4>
            <form:form method="post" modelAttribute="meeting" cssClass="form-group col-md-4" enctype="multipart/form-data">
                <form:hidden path="ownerId" value="${user.id}"/>
                <div class="form-group">
                    <label for="title">Tytył</label>
                    <form:input path="title" cssClass="form-control" id="title" placeholder="Tytuł wydarzenia"/>
                    <form:errors path="title" cssClass="invalid-feedback" element="div" />
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
                    <form:input path="meetTime" cssClass="form-control" id="meetTime" type="datetime-local" />
                    <form:errors path="meetTime" cssClass="error" element="div" />
                </div>
                <button type="submit" class="btn btn-primary">Dodaj nowe spotkanie</button>
            </form:form>
        </div>
    </div>
    <br>
</main>
<jsp:include page="footer.jsp"/>

