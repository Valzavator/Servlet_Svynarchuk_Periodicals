<html>
<head>
</head>
<body class="d-flex flex-column h-100">
<main role="main" class="container h-100">
    <div class="row h-100 justify-content-md-center align-items-center">
        <div class="card w-50 mx-auto">
            <div class="card-header h2 text-center">
                <fmt:message key="login"/>
            </div>
            <div class="card-body mx-auto w-100">
                <form accept-charset="UTF-8" role="form" method="post">
                    <div class="form-group">
                        <label for="inputEmail"><fmt:message key="email"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend">
                            <i class="fa fa-envelope fa-lg" aria-hidden="true"></i>
                        </span>
                            </div>
                            <input type="email" class="form-control form-control-lg is-valid" id="inputEmail"
                                   name="email"
                                   aria-describedby="emailHelp"
                                   placeholder="<fmt:message key="email.placeholder"/>" required>
                            <div class="valid-feedback">
                                Please choose a username.
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword"><fmt:message key="password"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2">
                                    <i class="fa fa-lock fa-2x" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="password" class="form-control form-control-lg is-invalid" id="inputPassword"
                                   name="password"
                                   placeholder="<fmt:message key="password.placeholder"/>" required>
                            <div class="invalid-tooltip">
                                Please choose a username.
                            </div>
                        </div>

                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="login"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>
