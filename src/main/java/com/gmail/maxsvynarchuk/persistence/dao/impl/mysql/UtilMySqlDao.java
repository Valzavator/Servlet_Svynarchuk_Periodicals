package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.connection.ConnectionFactory;
import com.gmail.maxsvynarchuk.persistence.connection.PooledConnection;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.exception.DaoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This helper class was created to avoid a lot of JDBC boilerplate code.
 *
 * @author Maksym Svynarchuk
 */
public class UtilMySqlDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilMySqlDao.class);

    private static final String ERROR_GENERATE_KEY = "Can't retrieve generated key";
    private static final String ERROR_EXECUTE_QUERY = "Failed to execute query";
    private static final String INVALID_PAGINATION_PARAMETERS = "Pagination parameters can`t be negative values";

    static final String SQL_LIMIT_ONE = " LIMIT 1";
    static final String LIMIT = "LIMIT ?,?";

    /**
     * Connection pool
     */
    private ConnectionFactory pool;

    /**
     * Converts data from ResultSet to domain object
     */
    private EntityMapper<T> mapper;

    public UtilMySqlDao(EntityMapper<T> mapper) {
        this(PooledConnection.getInstance(), mapper);
    }

    public UtilMySqlDao(ConnectionFactory pool, EntityMapper<T> mapper) {
        this.pool = pool;
        this.mapper = mapper;
    }

    /**
     * Retrieve one object from database which matches given query.
     *
     * @param query  raw sql syntax to select object. Can contains ? wildcard.
     * @param params parameters to substitute wildcards in query
     * @return Optional object, which contains retrieved object or null
     */
    public Optional<T> findOne(String query, Object... params) {
        List<T> results = findAll(query + SQL_LIMIT_ONE, params);
        return Optional.ofNullable(results.isEmpty() ? null : results.get(0));
    }

    /**
     * Retrieve all objects from database which match given query.
     *
     * @param query  raw sql syntax for objects selecting. Can contains ? wildcard.
     * @param params parameters to substitute wildcards in query
     * @return list of retrieved objects
     */
    public List<T> findAll(String query, Object... params) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(query)) {

            setParamsToStatement(statement, params);
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapper.mapToObjectList(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error(ERROR_EXECUTE_QUERY, e);
            throw new DaoException(ERROR_EXECUTE_QUERY, e);
        }
    }

//    /**
//     * Retrieve all objects from database which match given query
//     * in sub-range from {@code fromIndex} (inclusive) to
//     * {@code fromIndex + size} (exclusive) is within
//     * the bounds of range from 0 (inclusive) to  length (exclusive).
//     *
//     * @param query     raw sql syntax for objects selecting. Can contains ? wildcard.
//     * @param fromIndex the lower-bound (inclusive) of the sub-range
//     * @param size      the size of the sub-range
//     * @param params    parameters to substitute wildcards in query
//     * @return list of retrieved objects
//     */
//    public List<T> findAllWithPagination(String query, int fromIndex, int size, Object... params) {
//        if (fromIndex < 0 || size < 0) {
//            LOGGER.error(INVALID_PAGINATION_PARAMETERS);
//            throw new DaoException(INVALID_PAGINATION_PARAMETERS);
//        }
//
//        final String SQL_PAGINATION_LIMIT = String.format(" LIMIT %d, %d", fromIndex, size);
//        return findAll(query + SQL_PAGINATION_LIMIT, params);
//    }

    /**
     * Perform update of some table in database
     * based on given query and parameters.
     *
     * @param query  sql-based string, which specify update behavior
     * @param params parameters to substitute wildcards in query
     */
    public void executeUpdate(String query, Object... params) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParamsToStatement(statement, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(ERROR_EXECUTE_QUERY, e);
            throw new DaoException(ERROR_EXECUTE_QUERY, e);
        }
    }

    /**
     * Performs insertion for entities with generated primary key field.
     * For entities, which doesn't have auto-generated fields -
     * use {@link #executeUpdate(String, Object...)} method
     * to properly persist data.
     *
     * @param query  sql-based string, which specify details of insertion operation
     * @param params parameters to substitute wildcards in query
     * @param pkType entity primary key type
     * @return generated id
     */
    public <PK> PK executeInsertWithGeneratedPrimaryKey(String query,
                                                        Class<PK> pkType,
                                                        Object... params) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setParamsToStatement(statement, params);
            statement.executeUpdate();

            return getGeneratedPrimaryKey(statement, pkType);

        } catch (SQLException e) {
            LOGGER.error(ERROR_EXECUTE_QUERY, e);
            throw new DaoException(ERROR_EXECUTE_QUERY, e);
        }
    }

    public Long executeInsertWithGeneratedPrimaryKey(String query,
                                                     Object... params) {
        return executeInsertWithGeneratedPrimaryKey(query, Long.class, params);
    }

    /**
     * Sets all parameters to statement.
     *
     * @param statement PreparedStatement
     * @param params    parameters to substitute wildcards in raw query
     * @throws SQLException SQLException
     */
    private void setParamsToStatement(PreparedStatement statement, Object... params)
            throws SQLException {
        Objects.requireNonNull(params);

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                statement.setObject(i + 1, params[i]);
            } else {
                statement.setNull(i + 1, Types.OTHER);
            }
        }
    }

    /**
     * Get from resultSet generated by database primary key.
     * Use only after execution statement.
     * Statement must be in Statement.RETURN_GENERATED_KEYS mode.
     *
     * @param statement PreparedStatement
     * @param pkType    entity primary key type
     * @return generated key
     * @throws SQLException if statement doesn't generates key
     */
    private <PK> PK getGeneratedPrimaryKey(PreparedStatement statement, Class<PK> pkType)
            throws SQLException {
        if (Objects.isNull(pkType)) {
            throw new DaoException("Primary key type is null");
        }

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            if (pkType.isAssignableFrom(Integer.class)) {
                Integer key = rs.getInt(1);
                return pkType.cast(key);
            } else if (pkType.isAssignableFrom(Long.class)) {
                Long key = rs.getLong(1);
                return pkType.cast(key);
            } else {
                throw new DaoException("Unsupported key type");
            }
        } else {
            LOGGER.error(ERROR_GENERATE_KEY);
            throw new DaoException(ERROR_GENERATE_KEY);
        }
    }

}
