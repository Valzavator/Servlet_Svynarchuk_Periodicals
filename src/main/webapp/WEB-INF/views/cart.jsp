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
    <div class=" table-responsive">
        <table class="table table-striped table-hover text-center align-middle">
            <thead>
            <tr class="bg-primary">
                <th scope="col">№</th>
                <th scope="col">Назва</th>
                <th scope="col">Тип</th>
                <th scope="col">Періодичність</th>
                <th scope="col">Видавець</th>
                <th scope="col">План підписки</th>
                <th scope="col">Ціна</th>
                <th scope="col"></th>
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
                    Total price: 24 $
                </strong>
            </h1>
        </div>
        <div class="p-2 ml-auto">
            <button type="button" class="btn btn-success btn-lg">
                Большая кнопка
                &nbsp;<i class="fa fa-check fa-lg" aria-hidden="true"></i>
            </button>
        </div>
</main>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>