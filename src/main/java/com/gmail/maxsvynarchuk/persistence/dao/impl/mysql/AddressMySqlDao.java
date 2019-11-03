package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.AddressDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.AddressMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Address;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddressMySqlDao implements AddressDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("address.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("address.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("address.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("address.delete");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("address.where.id");

    private final UtilMySqlDao<Address> utilMySqlDao;

    public AddressMySqlDao() {
        this(new AddressMapper());
    }

    public AddressMySqlDao(EntityMapper<Address> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public AddressMySqlDao(UtilMySqlDao<Address> utilMySqlDao) {
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
