<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <div class="container-fluid p-3" style="background-color: darkolivegreen">
        <br><br><br>
        <span class="align-text-bottom"><h4  style="color: white"><i class="fas fa-sign-in-alt"></i> Logowanie użytkownika</h4></span>
    </div>


    <div class="container">
        <div class="row justify-content-sm-center">
            <div class="col-sm-4"></div>
            <div class="col-sm-4 rounded border p-5">
                <form:form method="post" modelAttribute="user" cssClass="form-group">
                    <span class="error" style="text-align: center;">${errorMsg}</span>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Adres e-mail</label>
                        <form:input path="email" cssClass="form-control" id="exampleInputEmail1"/>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        <form:errors path="email" cssClass="invalid-feedback" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="password">Hasło</label>
                        <form:password path="password" cssClass="form-control" id="password"/>
                        <form:errors path="password" cssClass="invalid-feedback" element="div" />
                    </div>
                    <button type="submit" class="btn btn-primary">Zaloguj</button>
                </form:form>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>

