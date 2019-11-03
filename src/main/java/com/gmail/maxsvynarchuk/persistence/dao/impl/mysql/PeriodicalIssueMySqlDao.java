package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.AddressDao;
import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.AddressMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Address;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PeriodicalIssueMySqlDao implements PeriodicalIssueDao {
    private final static String SELECT_ALL =
            "SELECT * FROM addresses ";

    private final static String INSERT =
            "INSERT INTO addresses " +
                    "(country, city, post_index, detail_address) " +
                    "VALUES(?, ?, ?, ?);";

    private final static String UPDATE =
            "UPDATE addresses SET " +
                    "country = ?, city = ?, " +
                    "post_index = ?, detail_address = ?";

    private final static String DELETE =
            "DELETE FROM addresses ";

    private final static String WHERE_ID =
            "WHERE address_id = ? ";


    private final UtilMySqlDao<Address> utilMySqlDao;

    public PeriodicalIssueMySqlDao() {
        this(new AddressMapper());
    }

    public PeriodicalIssueMySqlDao(EntityMapper<Address> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public PeriodicalIssueMySqlDao(UtilMySqlDao<Address> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Address> findOne(Long id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Address> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    @Override
    public List<Address> findAll(int skip, int limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Address insert(Address obj) {
        Objects.requireNonNull(obj);

        Long id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                obj.getCountry(),
                obj.getCity(),
                obj.getPostIndex(),
                obj.getDetailAddress());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Address obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getCountry(),
                obj.getCity(),
                obj.getPostIndex(),
                obj.getDetailAddress(),
                obj.getId());
    }

    @Override
    public void delete(Long id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }
}
