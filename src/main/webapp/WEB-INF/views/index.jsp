<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<c:url value="/" var="mainURL"/>
<main role="main" class="flex-shrink-0">
    <br>
    <p class="container">
        <div class="form-row">
            <div class="form-group col-md-8">
                <h4 class="cover-heading">Lista wszystkich tweet'ów </h4>
            </div>
        </div>
        <p>
            <img src="data:image/jpeg;base64,${principal.base64Image}" width="40" height="40"/>

            <p>
            <h3>Zmienna globalna dostępna dla wszystkich uzytkowników aplikacji : ${varible}</h3>
            </p>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

