package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.RoleDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.RoleMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.util.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RoleMySqlDao implements RoleDao {
    private final static String SELECT_ALL =
            ResourceManager.QUERIES.getProperty("role.select.all");
    private final static String INSERT =
            ResourceManager.QUERIES.getProperty("role.insert");
    private final static String UPDATE =
            ResourceManager.QUERIES.getProperty("role.update");
    private final static String DELETE =
            ResourceManager.QUERIES.getProperty("role.delete");
    private final static String COUNT =
            ResourceManager.QUERIES.getProperty("role.count");
    private final static String WHERE_ID =
            ResourceManager.QUERIES.getProperty("role.where.id");

    private final UtilMySqlDao<Role> utilMySqlDao;

    public RoleMySqlDao() {
        this(new RoleMapper());
    }

    public RoleMySqlDao(EntityMapper<Role> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public RoleMySqlDao(UtilMySqlDao<Role> utilMySqlDao) {
        this.utilMySqlDao = utilMySqlDao;
    }

    @Override
    public Optional<Role> findOne(Integer id) {
        return utilMySqlDao.findOne(SELECT_ALL + WHERE_ID, id);
    }

    @Override
    public List<Role> findAll() {
        return utilMySqlDao.findAll(SELECT_ALL);
    }

    public List<Role> findAll(long skip, long limit) {
        return utilMySqlDao.findAll(SELECT_ALL + UtilMySqlDao.LIMIT, skip, limit);
    }

    @Override
    public Role insert(Role obj) {
        Objects.requireNonNull(obj);

        Integer id = utilMySqlDao.executeInsertWithGeneratedPrimaryKey(
                INSERT,
                Integer.class,
                obj.getName());
        obj.setId(id);

        return obj;
    }

    @Override
    public void update(Role obj) {
        Objects.requireNonNull(obj);

        utilMySqlDao.executeUpdate(
                UPDATE + WHERE_ID,
                obj.getName(),
                obj.getId());
    }

    @Override
    public void delete(Integer id) {
        utilMySqlDao.executeUpdate(
                DELETE + WHERE_ID,
                id);
    }

    @Override
    public long getCount() {
        return utilMySqlDao.getRowsCount(COUNT);
    }
}
