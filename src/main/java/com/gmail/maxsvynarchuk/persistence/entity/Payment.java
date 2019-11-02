package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents payment made by some user purchasing some periodicals
 * @see User
 * @see Periodical
 */
public class Payment implements Serializable {
    Long id;
    User user;
    Date paymentDate;
    BigDecimal totalPrice;

    public static class Builder {
        private final Payment payment;

        public Builder() {
            this.payment = new Payment();
        }

        public Builder setId(Long id) {
            payment.setId(id);
            return this;
        }

        public Builder setUser(User user) {
            payment.setUser(user);
            return this;
        }

        public Builder setPaymentDate(Date paymentDate) {
            payment.setPaymentDate(paymentDate);
            return this;
        }

        public Builder setTotalPrice(BigDecimal totalPrice) {
            payment.setTotalPrice(totalPrice);
            return this;
        }

        public Payment build() {
            return payment;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Payment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("paymentDate=" + paymentDate)
                .add("totalPrice=" + totalPrice)
                .toString();
    }
}
