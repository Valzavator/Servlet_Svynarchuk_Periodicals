package com.gmail.maxsvynarchuk.persistence.transaction;

import com.gmail.maxsvynarchuk.persistence.connection.ConnectionProxy;
import com.gmail.maxsvynarchuk.persistence.connection.DataSourceProxy;
import com.gmail.maxsvynarchuk.persistence.exception.PersistenceException;
import com.gmail.maxsvynarchuk.persistence.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The interface was created for transaction support.
 * It's important to understand that it works in conjunction
 * with a datasource proxy and a connection proxy
 * which are inside connection package.
 *
 * @author Maksym Svynarchuk
 * @see DataSourceProxy
 * @see ConnectionProxy
 */
@FunctionalInterface
public interface Transaction {
    Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    int TRANSACTION_DEFAULT_LEVEL = Connection.TRANSACTION_SERIALIZABLE;

    void execute();

    static boolean doTransaction(Transaction t) {
        return doTransaction(t, TRANSACTION_DEFAULT_LEVEL);
    }

    static boolean doTransaction(Transaction t, int transactionIsolationLevel) {
        try (TransactionHelper transactionHelper =
                     new TransactionHelper(transactionIsolationLevel)) {

            LOGGER.debug("Transaction begin");
            transactionHelper.prepareConnectionForTransaction();
            try {
                t.execute();
                LOGGER.debug("Transaction body was successfully done");
            } catch (PersistenceException persistenceException) {
                LOGGER.debug("Transaction body fail. Trying rollback", persistenceException);
                try {
                    transactionHelper.doRollback();
                } catch (SQLException rollbackException) {
                    rollbackException.addSuppressed(persistenceException);
                    throw new TransactionException(rollbackException);
                }
            }
            transactionHelper.commit();
            transactionHelper.returnDefaultConnectionState();

            LOGGER.info("Transaction end");
            return transactionHelper.isTransactionDoneWithoutRollback();
        }
    }
}
