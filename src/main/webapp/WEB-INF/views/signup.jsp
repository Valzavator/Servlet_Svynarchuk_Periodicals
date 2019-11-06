<html>
<head>
</head>
<body class="d-flex flex-column min-vh-100">
<main role="main" class="container min-vh-100 mb-5">
    <div class="row min-vh-100 justify-content-md-center align-items-center">
        <div class="card w-75 mx-auto">
            <div class="card-header h2 text-center">
                <fmt:message key="signup"/>
            </div>
            <div class="card-body mx-auto w-100">
                <form>

                    <div class="form-group">
                        <label for="inputEmail"><fmt:message key="email"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend0">
                                    <i class="fa fa-envelope fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="email" class="form-control form-control-lg" id="inputEmail"
                                   placeholder="<fmt:message key="email.placeholder"/>" required>
                        </div>
                    </div>

                    <div class="form-group form-row justify-content-center">
                        <div class="col-auto mr-auto w-50">
                            <label for="validationFirstName"><fmt:message key="firstname"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrepend">
                                        <i class="fa fa-user fa-2x" aria-hidden="true"></i>
                                    </span>
                                </div>
                                <input type="text" class="form-control form-control-lg" id="validationFirstName"
                                       placeholder="<fmt:message key="firstname.placeholder"/>"
                                       aria-describedby="inputGroupPrepend" required>
                            </div>
                        </div>

                        <div class="col-auto ml-auto w-50">
                            <label for="validationLastName"><fmt:message key="lastname"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrepend2">
                                        <i class="fa fa-user fa-2x" aria-hidden="true"></i>
                                    </span>
                                </div>
                                <input type="text" class="form-control form-control-lg" id="validationLastName"
                                       placeholder="<fmt:message key="lastname.placeholder"/>"
                                       aria-describedby="inputGroupPrepend" required>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword"><fmt:message key="password"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend3">
                                    <i class="fa fa-lock fa-2x" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="password" class="form-control form-control-lg" id="inputPassword"
                                   placeholder="<fmt:message key="password.placeholder"/>" required>
                        </div>
                    </div>

                    <div class="form-group form-row">
                        <div class="col-auto mr-auto w-50">
                            <label for="inputGender"><fmt:message key="gender"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend4">
                                    <i class="fa fa-venus-mars fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <select id="inputGender" class="form-control form-control-lg">
                                    <option><fmt:message key="gender.male"/></option>
                                    <option><fmt:message key="gender.female"/></option>
                                </select>
                            </div>
                        </div>

                        <div class="col-auto mr-auto w-50">
                            <label for="inputDateOfBirth"><fmt:message key="dateofbirth"/></label>

                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend5">
                                    <i class="fa fa-calendar fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input type="date" class="form-control form-control-lg"
                                       max="3000-12-31" min="1900-01-01" id="inputDateOfBirth" required>
                            </div>
                        </div>

                    </div>

                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="signup"/></button>
                    </div>

                </form>
            </div>

            <div class="card-footer text-muted">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Holy guacamole!</strong> You should check in on some of those fields below.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Holy guacamole!</strong> You should check in on some of those fields below.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Holy guacamole!</strong> You should check in on some of those fields below.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>