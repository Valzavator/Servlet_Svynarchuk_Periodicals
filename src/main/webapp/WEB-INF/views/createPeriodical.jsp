<%--@elvariable id="errors" type="java.util.Map"--%>
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
<main role="main" class="container min-vh-100 mb-5">
    <div class="row min-vh-100 justify-content-md-center align-items-center">
        <div class="card w-75 mx-auto">
            <div class="card-header h2 text-center">
                <fmt:message key="admin.management.create.periodical"/>
            </div>
            <div class="card-body mx-auto w-100">

                <form accept-charset="UTF-8" role="form" method="post">

                    <div class="form-group">
                        <label for="name">
                            <fmt:message key="periodical.name"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend0">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="name"
                                   name="periodicalName"
                                   value="<c:out value="${requestScope.periodicalDTO.name}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorPeriodicalName}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.periodical.name.placeholder"/>"
                                   required>
                            <c:if test="${errors.errorPeriodicalName}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.periodical.name"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description">
                            <fmt:message key="periodical.description"/>
                        </label>
                        <textarea id="description"
                                  name="periodicalDescription"
                                  class="form-control
                                  <c:if test="${errors.errorPeriodicalDescription}">
                                            is-invalid
                                  </c:if>"
                                  placeholder="<fmt:message key="create.periodical.description.placeholder"/>"
                                  rows="5"
                                  required><c:out value="${requestScope.periodicalDTO.description}"/></textarea>
                        <c:if test="${errors.errorPeriodicalDescription}">
                            <div class="invalid-feedback">
                                <fmt:message key="error.periodical.description"/>
                            </div>
                        </c:if>
                    </div>


                    <div class="form-group">
                        <label for="price">
                            <fmt:message key="periodical.price"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend1">
                                    <i class="fa fa-usd fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="number" id="price"
                                   min="0" step="0.01"
                                   name="periodicalPrice"
                                   value="<c:out value="${requestScope.periodicalDTO.price}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorPeriodicalPrice}">
                                            is-invalid
                                  </c:if>"
                                   placeholder="<fmt:message key="create.periodical.price.placeholder"/>"
                                   required>
                            <c:if test="${errors.errorPeriodicalPrice}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.periodical.price"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="periodicalType">
                            <fmt:message key="periodical.type"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <select id="periodicalType"
                                    name="periodicalTypeId"
                                    class="form-control form-control-lg"
                                    required>
                                <c:forEach var="type" items="${requestScope.periodicalTypes}">
                                    <option value="${type.id}"
                                        <c:if test="${type.equals(requestScope.periodicalDTO.periodicalType)}">
                                            selected
                                        </c:if>
                                    >
                                        <c:out value="${type.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="frequency">
                            <fmt:message key="periodical.frequency"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend3">
                                    <i class="fa fa-files-o fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <select id="frequency"
                                    name="periodicalFrequencyId"
                                    class="form-control form-control-lg"
                                    required>
                                <c:forEach var="frequency" items="${requestScope.frequencies}">
                                    <option value="${frequency.id}"
                                        <c:if test="${frequency.equals(requestScope.periodicalDTO.frequency)}">
                                            selected
                                        </c:if>
                                    >
                                        <c:out value="${frequency.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="publisher">
                            <fmt:message key="periodical.publisher"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend4">
                                    <i class="fa fa-print fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <select id="publisher"
                                    name="periodicalPublisherId"
                                    class="form-control form-control-lg"
                                    required>
                                <c:forEach var="publisher" items="${requestScope.publishers}">
                                    <option value="${publisher.id}"
                                        <c:if test="${publisher.equals(requestScope.periodicalDTO.publisher)}">
                                            selected
                                        </c:if>
                                    >
                                        <c:out value="${publisher.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg mt-3">
                            <fmt:message key="admin.management.create.periodical"/>
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </div>
</main>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>