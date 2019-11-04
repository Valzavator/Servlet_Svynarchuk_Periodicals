<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myLib" uri="/WEB-INF/tag/requestedViewTag" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<c:set var="homeView" scope="page" value="/WEB-INF/views/index.jsp"/>
<c:set var="loginView" scope="page" value="/WEB-INF/views/login.jsp"/>
<c:set var="signUpView" scope="page" value="/WEB-INF/views/signup.jsp"/>

<c:set var="currView" scope="page">
    <myLib:viewUri/>
</c:set>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <a class="navbar-brand" href="<c:url value="/app/"/>">Periodicals</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
            aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li
                    <c:if test="${homeView.equals(currView)}">
                        class="active"
                    </c:if>
            >
                <a class="nav-link" href="<c:url value="/app/"/>">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i> <fmt:message key="home"/>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-language fa-lg" aria-hidden="true"></i>
                    ${sessionScope.locale.getLanguage().toUpperCase()}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <c:forEach items="${applicationScope.supportedLocales}" var="lang">
                        <a class="dropdown-item" href="?lang=${lang}">${lang.toUpperCase()}</a>
                    </c:forEach>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li
                    <c:if test="${signUpView.equals(currView)}">
                        class="active"
                    </c:if>
            >
                <a class="nav-link" href="<c:url value="/app/signup"/>">
                    <i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i> <fmt:message key="signup"/>
                </a>
            </li>
            <li
                    <c:if test="${loginView.equals(currView)}">
                        class="active"
                    </c:if>
            >
                <a class="nav-link" href="<c:url value="/app/login"/>">
                    <i class="fa fa-sign-in fa-lg" aria-hidden="true"></i> <fmt:message key="login"/>
                </a>
            </li>
        </ul>
    </div>
</nav>