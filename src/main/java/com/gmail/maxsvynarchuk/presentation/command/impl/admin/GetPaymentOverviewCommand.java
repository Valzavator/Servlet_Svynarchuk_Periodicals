package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetPaymentOverviewCommand implements Command {
    private final PaymentService paymentService =
            ServiceFactory.getPaymentService();
    private final SubscriptionService subscriptionService =
            ServiceFactory.getSubscriptionService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
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
            return CommandResult.forward(Views.PAYMENT_OVERVIEW_VIEW);
        } else {
            return CommandResult.forward(Views.ERROR_404_VIEW);
        }
    }
}
