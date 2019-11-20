package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Payment;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PaymentService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllPaymentsCommand implements Command {
    private final PaymentService paymentService =
            ServiceFactory.getPaymentService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long rowsCount = paymentService.getPaymentsCount();
        long skip = PaginationManager.manage(request, rowsCount);
        List<Payment> subscriptions = paymentService.findAllPayments(
                skip, PaginationManager.DEFAULT_RECORDS_PER_PAGE);
        request.setAttribute(Attributes.PAYMENTS, subscriptions);
        return CommandResult.forward(Views.PAYMENTS_VIEW);
    }
}
