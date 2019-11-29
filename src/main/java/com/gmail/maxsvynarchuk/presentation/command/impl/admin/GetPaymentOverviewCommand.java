package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.exception.NotFoundException;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetPaymentOverviewCommand implements Command {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GetPaymentOverviewCommand.class);
    private final PaymentService paymentService =
            ServiceFactory.getPaymentService();
    private final SubscriptionService subscriptionService =
            ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Attempt to get a payment overview page");
        Long paymentId = Long.valueOf(
                request.getParameter(RequestParameters.PAYMENT_ID));
        Optional<Payment> paymentOpt =
                paymentService.findPaymentById(paymentId);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            List<Subscription> subscription =
                    subscriptionService.findAllSubscriptionsByPayment(payment);
            request.setAttribute(Attributes.PAYMENT_DTO, payment);
            request.setAttribute(Attributes.SUBSCRIPTIONS, subscription);
            LOGGER.debug("Attempt to get a payment overview page is successful");
            return CommandResult.forward(Views.PAYMENT_OVERVIEW_VIEW);
        }
        LOGGER.debug("Payment with id {} doesn't exist", paymentId);
        throw new NotFoundException();
    }
}
