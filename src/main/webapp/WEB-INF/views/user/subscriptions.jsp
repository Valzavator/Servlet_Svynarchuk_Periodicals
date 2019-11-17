<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.gmail.maxsvynarchuk.util.type.PeriodicalStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<html>
<head>
    <jsp:include page="/WEB-INF/views/snippets/head.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>
<main role="main" class="container">
    <div class="d-flex justify-content-center align-items-center">
        <h1 class="display-3">
            <strong>
                <fmt:message key="active.subscriptions"/>
            </strong>
        </h1>
    </div>
    <div class="progress mb-5">
        <div class="progress-bar" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
    <c:choose>
        <c:when test="${empty requestScope.subscriptions}">
            <div class="d-flex justify-content-center align-items-center mb-5">
                <h1 class="display-4 text-info">
                    <span class="badge badge-info"><fmt:message key="subscriptions.empty"/></span>
                </h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class=" table-responsive">
                <table class="table table-striped table-hover text-center align-middle">
                    <thead>
                    <tr class="bg-primary">
                        <th scope="col" class="align-middle">№</th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.name"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="periodical.status"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.type"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.frequency"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="subscription.start.date"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="subscription.end.date"/></th>
                        <th scope="col" class="align-middle"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="subscription" items="${requestScope.subscriptions}" varStatus="counter">
                        <tr>
                            <th scope="row" class="align-middle">${counter.count}</th>
                            <td class="align-middle overflow-text">
                                <c:out value="${subscription.periodical.name}"/>
                            </td>
                            <td class="align-middle">
                                <c:if test="${subscription.periodical.status eq PeriodicalStatus.ACTIVE}">
                                <span class="badge badge-success">
                                    <fmt:message key="periodical.status.active"/>
                                </span>
                                </c:if>
                                <c:if test="${subscription.periodical.status eq PeriodicalStatus.SUSPENDED}">
                                <span class="badge badge-warning">
                                    <fmt:message key="periodical.status.suspended"/>
                                </span>
                                </c:if>
                            </td>
                            <td class="align-middle">
                                <c:out value="${subscription.periodical.periodicalType.name}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${subscription.periodical.frequency.name}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${subscription.startDate}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${subscription.endDate}"/>
                            </td>
                            <td class="align-middle">
                                <a href="<c:url value="/app/periodical?periodicalId=${subscription.periodical.id}"/>">
                                    <i class="fa fa-eye fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="subscription.see"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <jsp:include page="/WEB-INF/views/snippets/pagination.jsp"/>
        </c:otherwise>
    </c:choose>

</main>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>