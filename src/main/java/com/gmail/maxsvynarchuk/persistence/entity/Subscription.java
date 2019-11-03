package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Class that represents user subscription of some plan for periodicals
 *
 * @author Maksym Svynarchuk
 * @see User
 * @see SubscriptionPlan
 * @see Periodical
 */
public class Subscription implements Serializable {
    private static final long serialVersionUID = 8540619478891337776L;

    private Long id;
    private User user;
    private Periodical periodical;
    private SubscriptionPlan subscriptionPlan;
    private Date startDate;
    private Date endDate;

    public static class Builder {
        private final Subscription subscription;

        public Builder() {
            subscription = new Subscription();
        }

        public Builder setId(Long id) {
            subscription.setId(id);
            return this;
        }

        public Builder setUser(User user) {
            subscription.setUser(user);
            return this;
        }

        public Builder setPeriodical(Periodical periodical) {
            subscription.setPeriodical(periodical);
            return this;
        }

        public Builder setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
            subscription.setSubscriptionPlan(subscriptionPlan);
            return this;
        }

        public Builder setStartDate(Date startDate) {
            subscription.setStartDate(startDate);
            return this;
        }

        public Builder setEndDate(Date endDate) {
            subscription.setEndDate(endDate);
            return this;
        }

        public Subscription build() {
            return subscription;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Subscription() {
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

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subscription.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("periodical=" + periodical)
                .add("subscriptionPlan=" + subscriptionPlan)
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .toString();
    }
}
