package com.gmail.maxsvynarchuk.presentation.util.validator;

import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.presentation.command.impl.authorization.PostSignUpCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.DescriptionValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.IssueNumberValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.TitleValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostSignUpCommand#execute}
 *
 * @author Maksym Svynarhcuk
 */
public class IssueValidatorManager extends ValidatorManager {

    @Override
    protected void validateObject(Map<String, Boolean> errors, Serializable object) {
        if (!(object instanceof PeriodicalIssue)) {
            throw new IllegalArgumentException();
        }
        PeriodicalIssue issue = (PeriodicalIssue) object;
        validateField(new TitleValidator(),
                issue.getName(),
                Attributes.ERROR_ISSUE_NAME,
                errors);
        validateField(new IssueNumberValidator(),
                issue.getIssueNumber(),
                Attributes.ERROR_ISSUE_NUMBER,
                errors);
        validateField(new DescriptionValidator(),
                issue.getDescription(),
                Attributes.ERROR_ISSUE_DESCRIPTION,
                errors);
    }
}
