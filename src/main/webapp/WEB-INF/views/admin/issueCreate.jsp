<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <div class="card w-75 mx-auto">
            <div class="card-header h2 text-center">
                <fmt:message key="admin.management.create.add.issue"/>
            </div>
            <div class="card-body mx-auto w-100">

                <form accept-charset="UTF-8" role="form" method="post">
                    <input type="hidden" name="periodicalId" value="${requestScope.periodicalDTO.id}"/>

                    <div class="form-group">
                        <label for="name">
                            <fmt:message key="issue.name"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend0">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="name"
                                   name="issueName"
                                   value="<c:out value="${requestScope.issueDTO.name}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorIssueName}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.issue.name.placeholder"/>"
                                   required>
                        <c:if test="${errors.errorIssueName}">
                            <div class="invalid-feedback">
                                <fmt:message key="error.issue.name"/>
                            </div>
                        </c:if>
                        </div>
                    </div>



                    <div class="form-group form-row">

                        <div class="col-auto mr-auto w-50">
                            <label for="number">
                                <fmt:message key="issue.number"/>
                            </label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend1">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input type="text" id="number"
                                       name="issueNumber"
                                       value="<c:out value="${requestScope.issueDTO.issueNumber}"/>"
                                       class="form-control form-control-lg
                                   <c:if test="${errors.errorIssueNumber}">
                                            is-invalid
                                   </c:if>"
                                       placeholder="<fmt:message key="create.issue.number.placeholder"/>"
                                       required>
                                <c:if test="${errors.errorIssueNumber}">
                                    <div class="invalid-feedback">
                                        <fmt:message key="error.issue.number"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="col-auto mr-auto w-50">
                            <label for="publicationDate"><fmt:message key="dateofbirth"/></label>

                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend5">
                                    <i class="fa fa-calendar fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input type="date" id="publicationDate"
                                       name="publicationDate"
                                       value="<fmt:formatDate type = "date" value="${requestScope.issueDTO.publicationDate}" pattern="yyyy-MM-dd"/>"
                                       class="form-control form-control-lg"
                                       max="3000-12-31" min="2019-01-01" required>
                            </div>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="description">
                            <fmt:message key="issue.description"/>
                        </label>
                        <textarea id="description"
                                  name="issueDescription"
                                  class="form-control
                                  <c:if test="${errors.errorIssueDescription}">
                                            is-invalid
                                  </c:if>"
                                  placeholder="<fmt:message key="create.issue.description.placeholder"/>"
                                  rows="5"><c:out value="${requestScope.issueDTO.description}"/></textarea>
                        <c:if test="${errors.errorIssueDescription}">
                            <div class="invalid-feedback">
                                <fmt:message key="error.issue.description"/>
                            </div>
                        </c:if>
                    </div>

                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg mt-3">
                            <fmt:message key="admin.management.create.add.issue"/>
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