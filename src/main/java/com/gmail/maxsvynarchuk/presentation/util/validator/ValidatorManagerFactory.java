package com.gmail.maxsvynarchuk.presentation.util.validator;

public class ValidatorManagerFactory {
    public static ValidatorManager getSignInValidator() {
        return new SignInValidatorManager();
    }

    public static ValidatorManager getSignUpValidator() {
        return new SignUpValidatorManager();
    }

    public static ValidatorManager getPeriodicalValidator() {
        return new PeriodicalValidatorManager();
    }

    public static ValidatorManager getIssueValidator() {
        return new IssueValidatorManager();
    }
}
