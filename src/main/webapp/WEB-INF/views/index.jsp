<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>

<%--<fmt:setLocale value="${sessionScope.locale}"/>--%>
<%--<fmt:setBundle basename="i18n.lang"/>--%>

<html>
<head>

<%--    <jsp:include page="/WEB-INF/views/snippets/header.jsp"/>--%>

</head>
<body class="d-flex flex-column min-vh-100">

<%--<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>--%>

<main role="main" class="container">
    <div class="text-center">
        <img src="<c:url value="/resources/images/logo.png"/>" alt="Logo"/>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4 text-break"><fmt:message key="title"/></h1>
                <hr class="my-4">
                <p class="h4 text-justify"><fmt:message key="site.description"/></p>
            </div>
        </div>
    </div>
</main>

<%--<%@ include file="/WEB-INF/views/snippets/footer.jsp" %>--%>
</body>
</html>