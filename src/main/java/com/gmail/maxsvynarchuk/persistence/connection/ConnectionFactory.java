package com.gmail.maxsvynarchuk.persistence.connection;

import java.sql.Connection;

/**
 * The interface was created for getting database connection.
 *
 * @author Maksym Svynarchuk
 */
public interface ConnectionFactory {
    Connection getConnection();
}
