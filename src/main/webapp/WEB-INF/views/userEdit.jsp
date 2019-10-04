<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">

    <div class="container-fluid p-3" style="background-color: darkolivegreen">
        <br><br><br>
        <span class="align-text-bottom" style="color: white"><h4><i class="fas fa-user-cog"></i> Edycja danych użytkownika</h4></span>
    </div>
    <div class="container">
        <div class="row justify-content-sm-center">
            <div class="col-sm-4"></div>
            <div class="col-sm-4 rounded border p-3 whiteBg">
                <form:form method="post" modelAttribute="data" cssClass="form-group" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="firstName">Imię</label>
                        <form:input path="firstName" cssClass="form-control" id="firstName"/>
                        <form:errors path="firstName" cssClass="invalid-feedback" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="lastName">Nazwisko</label>
                        <form:input path="lastName" cssClass="form-control" id="lastName"/>
                        <form:errors path="lastName" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="email">Adres e-mail</label>
                        <form:input path="email" cssClass="form-control" id="email" readonly="true"/>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        <form:errors path="email" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="avatar">Avatar</label><br>
                        <img class="border border-info rounded" src="data:image/jpeg;base64,${data.base64Image}" width="70" height="70"/><br><br>
                        <form:hidden path="base64Image"/>
                        <form:input type="file" path="avatar" cssClass="form-control-file" id="avatar"/>
                        <form:errors path="avatar" cssClass="error" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="password">Hasło</label>
                        <form:password path="password" cssClass="form-control" id="password"/>
                        <form:errors path="password" cssClass="error" element="div" />
                    </div>

                    <div class="form-group">
                        <label for="rePassword">Powtórz hasło</label>
                        <form:password path="rePassword" cssClass="form-control" id="rePassword"/>
                        <form:errors path="rePassword" cssClass="error" element="div" />
                    </div>
                    <button type="submit" class="btn btn-primary">Dodaj nowego użytkownika</button>
                </form:form>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
    <br>
</main>
<jsp:include page="footer.jsp"/>

