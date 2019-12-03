package com.gmail.maxsvynarchuk.presentation.util.validator;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.PostCreatePeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.command.impl.admin.PostEditPeriodicalCommand;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.DescriptionValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.PeriodicalPriceValidator;
import com.gmail.maxsvynarchuk.presentation.util.validator.impl.TitleValidator;

import java.io.Serializable;
import java.util.Map;

/**
 * Validation data in {@link PostCreatePeriodicalCommand#execute}
 * and {@link PostEditPeriodicalCommand#execute}
 *
 * @author Maksym Svynarhcuk
 */
public class PeriodicalValidatorManager extends ValidatorManager {

    @Override
    protected void validateObject(Map<String, Boolean> errors, Serializable object) {
        if (!(object instanceof Periodical)) {
            throw new IllegalArgumentException("The object is not a type of Periodical");
        }
        Periodical periodical = (Periodical) object;
        validateField(new TitleValidator(),
                periodical.getName(),
                Attributes.ERROR_PERIODICAL_NAME,
                errors);
        validateField(new DescriptionValidator(),
                periodical.getDescription(),
                Attributes.ERROR_PERIODICAL_DESCRIPTION,
                errors);
        validateField(new PeriodicalPriceValidator(),
                periodical.getPrice(),
                Attributes.ERROR_PERIODICAL_PRICE,
                errors);
    }
}
