package com.gmail.maxsvynarchuk.presentation.util.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.persistence.entity.User;

public class RequestMapperFactory {
    private static final RequestEntityMapper<User> SIGN_IN_MAPPER = new SignInRequestMapper();
    private static final RequestEntityMapper<User> SIGN_UP_MAPPER = new SignUpRequestMapper();
    private static final RequestEntityMapper<Periodical> CREATE_PERIODICAL_MAPPER = new CreatePeriodicalRequestMapper();
    private static final RequestEntityMapper<Periodical> EDIT_PERIODICAL_MAPPER = new EditPeriodicalRequestMapper();
    private static final RequestEntityMapper<PeriodicalIssue> CREATE_ISSUE_MAPPER = new CreateIssueRequestMapper();

    public static RequestEntityMapper<User> getSignInMapper() {
        return SIGN_IN_MAPPER;
    }

    public static RequestEntityMapper<User> getSignUpMapper() {
        return SIGN_UP_MAPPER;
    }

    public static RequestEntityMapper<Periodical> getCreatePeriodicalMapper() {
        return CREATE_PERIODICAL_MAPPER;
    }

    public static RequestEntityMapper<Periodical> getEditPeriodicalMapper() {
        return EDIT_PERIODICAL_MAPPER;
    }

    public static RequestEntityMapper<PeriodicalIssue> getCreateIssueMapper() {
        return CREATE_ISSUE_MAPPER;
    }
}
