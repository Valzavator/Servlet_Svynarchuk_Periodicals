<html>
<head>
</head>
<body class="d-flex flex-column min-vh-100">

<main role="main" class="container">

    <c:forEach var="periodical" items="${requestScope.periodicals}">
        <div class="card border-dark mb-5">
            <div class="row no-gutters ">
                <div class="col-md-4 d-flex align-content-center flex-wrap">
                    <img src="<c:url value="/resources/images/logo.png"/>" class="card-img" alt="logo">
                </div>
                <div class="col-md-8 text-white">
                    <div class="card-header card-title text-center">
                        <h3><c:out value="${periodical.name}"/></h3>
                    </div>
                    <div class="card-body bg-primary accordion" id="accordion-${periodical.id}">
                        <div class="card-text text-center" id="heading-${periodical.id}">
                            <button class="btn btn-link" type="button" data-toggle="collapse"
                                    data-target="#collapse-${periodical.id}"
                                    aria-expanded="false" aria-controls="collapse-${periodical.id}">
                                <fmt:message key="periodical.description"/>&nbsp;
                                <i class="fa fa-caret-square-o-down fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                        <div id="collapse-${periodical.id}" class="collapse" aria-labelledby="heading-${periodical.id}"
                             data-parent="#accordion-${periodical.id}">
                            <p class="card-text"><c:out value="${periodical.description}"/></p>
                        </div>
                    </div>

                    <ul class="bg-primary text-white list-group list-group-flush">
                        <li class="list-group-item bg-primary">
                            <fmt:message key="periodical.type"/>: <c:out value="${periodical.periodicalType.name}"/>
                        </li>
                        <li class="list-group-item bg-primary">
                            <fmt:message key="periodical.frequency"/>: <c:out value="${periodical.frequency.name}"/>
                        </li>
                        <li class="list-group-item bg-primary">
                            <fmt:message key="periodical.publisher"/>: <c:out value="${periodical.publisher.name}"/>
                        </li>
                        <li class="list-group-item bg-primary">
                            <fmt:message key="periodical.price"/>: <c:out value="${periodical.price} $"/></li>
                    </ul>
                    <div class="card-footer d-flex justify-content-sm-center justify-content-lg-end ">
                        <form accept-charset="UTF-8" role="form" method="post" action="<c:url value="/app/cart/add"/>">
                            <!-- Button trigger modal -->
                            <div class="input-group ">
                                <input type="hidden" class="form-control" name="periodicalId" value="${periodical.id}">
                                <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                                        data-target="#modal-${periodical.id}">
                                    <i class="fa fa-chevron-circle-right fa-lg" aria-hidden="true">&nbsp;</i>
                                    <fmt:message key="periodical.subscribe"/>
                                </button>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="modal-${periodical.id}" tabindex="-1" role="dialog"
                                 aria-labelledby="modal-${periodical.id}" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">

                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">
                                                <fmt:message key="periodical.choose.subscription.plan"/>
                                            </h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <select class="custom-select" name="subscriptionPlan" required>
                                                    <c:forEach var="subscriptionPlanId"
                                                               items="${requestScope.subscriptionPlans}">
                                                        <option value="${subscriptionPlan.id}">
                                                            <c:out value="${subscriptionPlan.name}"/>
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">
                                                <i class="fa fa-chevron-circle-left fa-lg" aria-hidden="true">&nbsp;</i>
                                                <fmt:message key="periodical.back"/>
                                            </button>
                                            <button type="submit" class="btn btn-info">
                                                <i class="fa fa-shopping-cart fa-lg" aria-hidden="true">&nbsp;</i>
                                                <fmt:message key="periodical.add.to.cart"/>
                                            </button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <%@ include file="/WEB-INF/jspf/pagination.jspf" %>

</main>

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
</body>
</html>
