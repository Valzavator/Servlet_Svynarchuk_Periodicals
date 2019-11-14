<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <fmt:message key="admin.management.catalog"/>
            </strong>
        </h1>
    </div>
    <div class="progress mb-5">
        <div class="progress-bar" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
    <c:choose>
        <c:when test="${empty requestScope.catalog}">
            <div class="d-flex justify-content-center align-items-center mb-5">
                <h1 class="display-4 ">
                    <span class="badge badge-info"><fmt:message key="admin.management.catalog.empty"/></span>
                </h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class=" table-responsive">
                <table class="table table-striped table-hover text-center align-middle">
                    <thead>
                    <tr class="bg-primary">
                        <th scope="col">№</th>
                        <th scope="col"><fmt:message key="periodical.name"/></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="periodical" items="${requestScope.catalog}" varStatus="counter">
                        <tr>
                            <th scope="row" class="align-middle">${counter.count}</th>
                            <td class="align-middle overflow-text"><c:out value="${periodical.name}"/></td>
                            <td class="align-middle">
                                <a href="<c:url value="/app/periodical?periodicalId=${periodical.id}"/>">
                                    <i class="fa fa-eye fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="admin.management.catalog.see"/>
                                </a>
                            </td>
                            <td class="align-middle">
                                <a href="edit_periodical?id=${periodical.id}">
                                    <i class="fa fa-plus fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="admin.management.create.add.issue"/>
                                </a>
                            </td>
                            <td class="align-middle">
                                <a href="<c:url value="/app/admin/catalog/edit?periodicalId=${periodical.id}"/>">
                                    <i class="fa fa-pencil fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="admin.management.catalog.edit"/>
                                </a>
                            </td>
                            <td class="align-middle">
                                <a href="edit_periodical?id=${periodical.id}">
                                    <i class="fa fa-trash fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="admin.management.catalog.delete"/>
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

<!-- Modal -->
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<%--<c:if test="${true}">--%>
<%--    <script type="text/javascript" defer>--%>
<%--        $(document).ready(function () {--%>
<%--            $("#errorModal").modal("show");--%>
<%--        });--%>
<%--    </script>--%>
<%--</c:if>--%>
</body>
</html>