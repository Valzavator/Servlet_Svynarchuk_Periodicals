package com.gmail.maxsvynarchuk.provider;

import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.util.type.Gender;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import com.gmail.maxsvynarchuk.util.type.RoleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityProvider {
    public static SubscriptionPlan getOneMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(1)
                .setRate(new BigDecimal(1))
                .build();
    }

    public static SubscriptionPlan getThreeMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(3)
                .setRate(new BigDecimal("0.9"))
                .build();
    }

    public static SubscriptionPlan getSixMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(6)
                .setRate(new BigDecimal("0.8"))
                .build();
    }

    public static SubscriptionPlan getTwelveMonthSubscriptionPlan() {
        return SubscriptionPlan.newBuilder()
                .setMonthsAmount(12)
                .setRate(new BigDecimal("0.7"))
                .build();
    }
}
