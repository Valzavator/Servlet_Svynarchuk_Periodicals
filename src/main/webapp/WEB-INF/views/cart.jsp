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
        <h1 class="display-4">
            <strong>
                <fmt:message key="cart.empty"/>
            </strong>
        </h1>
    </div>
    <div class=" table-responsive">
        <table class="table table-striped table-hover text-center align-middle">
            <thead>
            <tr class="bg-primary">
                <th scope="col" class="align-middle">№</th>
                <th scope="col" class="align-middle"><fmt:message key="cart.name"/></th>
                <th scope="col" class="align-middle"><fmt:message key="cart.type"/></th>
                <th scope="col" class="align-middle"><fmt:message key="cart.frequency"/></th>
                <th scope="col" class="align-middle"><fmt:message key="cart.publisher"/></th>
                <th scope="col" class="align-middle"><fmt:message key="cart.subscription.plan"/></th>
                <th scope="col" class="align-middle"><fmt:message key="cart.price"/></th>
                <th scope="col">
                    <form method="get" class="align-middle">
                        <input  name="id" value="1" type="hidden"/>
                        <button type="submit" class="btn btn-danger btn-sm">
                            <fmt:message key="cart.remove.all"/>
                        </button>
                    </form>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row" class="align-middle">1</th>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Otto</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">
                    <form method="get">
                        <input  name="id" value="1" type="hidden"/>
                        <button type="submit" class="btn btn-link" style="color: red;">
                            <i class="fa fa-times-circle-o fa-lg" aria-hidden="true"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <tr>
                <th scope="row" class="align-middle">1</th>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Otto</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">
                    <form method="get">
                        <input  name="id" value="1" type="hidden"/>
                        <button type="submit" class="btn btn-link" style="color: red;">
                            <i class="fa fa-times-circle-o fa-lg" aria-hidden="true"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <tr>
                <th scope="row" class="align-middle">1</th>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Mark</td>
                <td class="align-middle">Otto</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">@mdo</td>
                <td class="align-middle">
                    <form method="get">
                        <input  name="id" value="1" type="hidden"/>
                        <button type="submit" class="btn btn-link" style="color: red;">
                            <i class="fa fa-times-circle-o fa-lg" aria-hidden="true"></i>
                        </button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
    </div>
    <div class="d-flex align-items-center">
        <div class="p-2">
            <h1 class="display-4">
                <strong>
                    <fmt:message key="cart.total.price"/>: 24 $
                </strong>
            </h1>
        </div>
        <div class="p-2 ml-auto">
            <button type="button" class="btn btn-success btn-lg">
                <fmt:message key="cart.pay"/>
                &nbsp;<i class="fa fa-check fa-lg" aria-hidden="true"></i>
            </button>
        </div>
    </div>
</main>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>