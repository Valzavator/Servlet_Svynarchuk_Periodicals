<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<html>
<head>

    <jsp:include page="/WEB-INF/views/snippets/header.jsp"/>

    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/error.css"/>">

</head>
<body class="d-flex flex-column h-100">

<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>

<main role="main" class="container h-100">
    <div class="row h-100 justify-content-md-center align-items-center">
        <div class=" text-center">
            <h1 class="default-status-error"><fmt:message key="error.default.status"/></h1>
            <p class="default-text-muted-error"><fmt:message key="error.default.message"/></p>
            <a class="btn btn-lg btn-primary mt-5" href="<c:url value="/app/"/>" role="button">
                <fmt:message key="error.backbtn"/>
            </a>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>

</body>
</html>