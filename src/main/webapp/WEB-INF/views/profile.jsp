<%--@elvariable id="periodical" type="com.gmail.maxsvynarchuk.persistence.entity.Periodical"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.gmail.maxsvynarchuk.util.type.PeriodicalStatus, com.gmail.maxsvynarchuk.util.type.Gender" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<html>
<head>
    <jsp:include page="/WEB-INF/views/snippets/head.jsp"/>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>
<main role="main" class="container h-100">
    <div class="row h-100 justify-content-md-center align-items-center">
        <div class="card border-dark w-100">
            <div class="row no-gutters ">
                <div class="col-md-4 d-flex align-content-center flex-wrap">
                    <c:choose>
                        <c:when test="${sessionScope.user.gender eq Gender.MALE}">
                            <img src="<c:url value="/resources/images/male.png"/>" class="card-img" alt="user-logo">
                        </c:when>
                        <c:when test="${sessionScope.user.gender eq Gender.FEMALE}">
                            <img src="<c:url value="/resources/images/female.png"/>" class="card-img" alt="user-logo">
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value="/resources/images/logo.png"/>" class="card-img" alt="user-logo">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-8 text-white bg-primary">
                    <div class="card-header card-title text-center">
                        <h3>
                            <c:out value="${sessionScope.user.firstName}"/>
                            <c:out value="${sessionScope.user.lastName}"/>
                        </h3>
                    </div>
                    <ul class="bg-primary text-white list-group list-group-flush">
                        <li class="list-group-item bg-primary">
                            <dl class="row">
                                <dt class="col-sm-3"><fmt:message key="role"/>:</dt>
                                <dd class="col-sm-9">
                                     <span class="badge badge-success">
                                        <c:out value="${sessionScope.user.role.name}"/>
                                    </span>
                                </dd>
                            </dl>
                        </li>
                        <li class="list-group-item bg-primary">
                            <dl class="row">
                                <dt class="col-sm-3"><fmt:message key="email"/>:</dt>
                                <dd class="col-sm-9">
                                    <c:out value="${sessionScope.user.email}"/>
                                </dd>
                            </dl>
                        </li>
                        <li class="list-group-item bg-primary">
                            <dl class="row">
                                <dt class="col-sm-3"><fmt:message key="dateofbirth"/>:</dt>
                                <dd class="col-sm-9">
                                    <c:out value="${sessionScope.user.dateOfBirth}"/>
                                </dd>
                            </dl>
                        </li>
                        <li class="list-group-item bg-primary">
                            <dl class="row">
                                <dt class="col-sm-3"><fmt:message key="gender"/>:</dt>
                                <dd class="col-sm-9">
                                    <span class="badge badge-success">
                                        <c:out value="${sessionScope.user.gender}"/>
                                    </span>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/snippets/pagination.jsp"/>
</main>

<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>

</body>
</html>
