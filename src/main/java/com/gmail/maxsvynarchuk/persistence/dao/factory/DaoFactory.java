package com.gmail.maxsvynarchuk.persistence.dao.factory;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.exception.DaoException;
import com.gmail.maxsvynarchuk.util.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * Factory that creates DAO entities
 *
 * @author Maksym Svynarhcuk
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);
    private static final String DB_CLASS = "factory.class";

    private static volatile DaoFactory instance;

    /**
     * Gets factory class name from certain properties file.
     * Reflection used for more flexibility.
     *
     * @return specific implemented factory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        String className = ResourceManager.DATABASE.getProperty(DB_CLASS);
                        instance = (DaoFactory) Class.forName(className).getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        LOGGER.error("Failed to initialize DaoFactory", e);
                        throw new DaoException(e);
                    }
                }
            }
        }
        return instance;
    }

    public abstract AddressDao getAddressDao();

    public abstract FrequencyDao getFrequencyDao();

    public abstract PaymentDao getPaymentDao();

    public abstract PeriodicalDao getPeriodicalDao();

    public abstract PeriodicalIssueDao getPeriodicalIssueDao();

    public abstract PeriodicalTypeDao getPeriodicalTypeDao();

    public abstract PublisherDao getPublisherDao();

    public abstract RoleDao getRoleDao();

    public abstract SubscriptionDao getSubscriptionDao();

    public abstract SubscriptionPlanDao getSubscriptionPlanDao();

    public abstract UserDao getUserDao();
}
