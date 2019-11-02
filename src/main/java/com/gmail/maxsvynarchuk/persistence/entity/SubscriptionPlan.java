package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents subscription plan for periodicals
 * @see Periodical
 */
public class SubscriptionPlan implements Serializable {
    Integer id;
    String name;
    Integer monthsAmount;
    BigDecimal rate;
    String description;

    public static class Builder {
        private final SubscriptionPlan subscriptionPlan;

        public Builder() {
            subscriptionPlan = new SubscriptionPlan();
        }

        public Builder setId(Integer id) {
            subscriptionPlan.setId(id);
            return this;
        }

        public Builder setName(String name) {
            subscriptionPlan.setName(name);
            return this;
        }

        public Builder setMonthsAmount(Integer monthsAmount) {
            subscriptionPlan.setMonthsAmount(monthsAmount);
            return this;
        }

        public Builder setRate(BigDecimal rate) {
            subscriptionPlan.setRate(rate);
            return this;
        }

        public Builder setDescription(String description) {
            subscriptionPlan.setDescription(description);
            return this;
        }

        public SubscriptionPlan build() {
            return subscriptionPlan;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public SubscriptionPlan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonthsAmount() {
        return monthsAmount;
    }

    public void setMonthsAmount(Integer monthsAmount) {
        this.monthsAmount = monthsAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPlan that = (SubscriptionPlan) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SubscriptionPlan.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("monthsAmount=" + monthsAmount)
                .add("rate=" + rate)
                .add("description='" + description + "'")
                .toString();
    }
}
