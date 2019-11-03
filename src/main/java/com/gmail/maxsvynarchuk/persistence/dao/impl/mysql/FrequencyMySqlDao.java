package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.FrequencyDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.FrequencyMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Frequency;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FrequencyMySqlDao implements FrequencyDao {
    private final static String SELECT_ALL =
            "SELECT * FROM frequencies ";

    private final static String INSERT =
            "INSERT INTO frequencies " +
                    "(frequency_name, meaning) " +
                    "VALUES(?, ?) ";

    private final static String UPDATE =
            "UPDATE frequencies SET " +
                    "frequency_name = ?, meaning = ? ";

    private final static String DELETE =
            "DELETE FROM frequencies ";

    private final static String WHERE_ID =
            "WHERE frequency_id = ? ";


    private final UtilMySqlDao<Frequency> utilMySqlDao;

    public FrequencyMySqlDao() {
        this(new FrequencyMapper());
    }

    public FrequencyMySqlDao(EntityMapper<Frequency> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public FrequencyMySqlDao(UtilMySqlDao<Frequency> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Frequency> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Frequency> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Frequency> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Frequency insert(Frequency obj) {
        Objects.requireNonNull(obj);

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getName(),
                obj.getMeaning());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Frequency obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getMeaning(),
                obj.getId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }
}
