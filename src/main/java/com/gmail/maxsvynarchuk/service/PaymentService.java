package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PaymentDao;
import com.gmail.maxsvynarchuk.persistence.dao.factory.DaoFactory;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Intermediate layer between command layer and dao layer.
 * Service responsible for processing payment-related operations
 *
 * @author Maksym Svynarchuk
 */
public class PaymentService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(PaymentService.class);
    private final PaymentDao paymentDao =
            DaoFactory.getInstance().getPaymentDao();

    private PaymentService() {
    }

    private static class Singleton {
        private final static PaymentService INSTANCE = new PaymentService();
    }

    public static PaymentService getInstance() {
        return Singleton.INSTANCE;
    }

    public Optional<Payment> findPaymentById(Long id) {
        LOGGER.debug("Attempt to find payment by id");
        return paymentDao.findOne(id);
    }

    public List<Payment> findAllPayments(long skip, long limit) {
        LOGGER.debug("Attempt to find all payments");
        return paymentDao.findAll(skip, limit);
    }

    public Payment createPayment(User user, BigDecimal totalPrice) {
        LOGGER.debug("Attempt to create payment");
        Objects.requireNonNull(user);
        Objects.requireNonNull(totalPrice);

        Payment payment = Payment.newBuilder()
                .setUser(user)
                .setTotalPrice(totalPrice)
                .setPaymentDate(LocalDateTime.now())
                .build();

        return paymentDao.insert(payment);
    }

    public long getPaymentsCount() {
        LOGGER.debug("Attempt to get payments count");
        return paymentDao.getCount();
    }
}
