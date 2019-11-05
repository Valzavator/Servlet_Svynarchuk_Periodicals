<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/error.css"/>">
</head>
<body class="d-flex flex-column h-100">
<main role="main" class="container h-100">
    <div class="row h-100 justify-content-md-center align-items-center">
        <div class=" text-center">
            <h1 class="default-status-error"><fmt:message key="error.default.status"/></h1>
            <p class="default-text-muted-error"><fmt:message key="error.default.message"/></p>
            <a class="btn btn-lg btn-primary mt-5" href="<c:url value="/app/"/>" role="button">
                <fmt:message key="error.backbtn"/>
            </a>
        </div>
    </div>
</main>
</body>
</html>
