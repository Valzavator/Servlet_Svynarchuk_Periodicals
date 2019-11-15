<%--@elvariable id="numberOfPages" type="java.lang.Integer"--%>
<%--@elvariable id="page" type="java.lang.Integer"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.gmail.maxsvynarchuk.presentation.util.constants.Views" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myLib" uri="/WEB-INF/tag/requestedViewTag" %>

<c:set var="catalogView" scope="page" value="${Views.CATALOG_VIEW}"/>
<c:set var="adminCatalogView" scope="page" value="${Views.ADMIN_CATALOG_VIEW}"/>
<c:set var="currView" scope="page">
    <myLib:viewUri/>
</c:set>
<c:choose>
    <c:when test="${currView.equals(catalogView)}">
        <c:set var="url" value="/app/catalog"/>
    </c:when>
    <c:when test="${currView.equals(adminCatalogView)}">
        <c:set var="url" value="/app/admin/catalog"/>
    </c:when>
</c:choose>


<c:if test="${numberOfPages gt 1}">
    <nav aria-label="Page navigation" class="mb-5">
        <ul class="pagination pagination-lg justify-content-center">
            <li class="page-item <c:if test="${page eq 1}">disabled</c:if>">
                <a class="page-link" href="<c:url value="/app/catalog?page=${page - 1}"/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${numberOfPages}" varStatus="counter">
                <li class="page-item <c:if test="${page eq counter.count}">disabled</c:if>">
                    <a class="page-link" href="<c:url value="${url}?page=${counter.count}"/>">
                            ${counter.count}
                    </a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${page eq numberOfPages}">disabled</c:if>">
                <a class="page-link" href="<c:url value="${url}?page=${page + 1}"/>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
