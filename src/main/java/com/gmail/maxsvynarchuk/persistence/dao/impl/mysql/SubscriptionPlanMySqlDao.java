package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql;

import com.gmail.maxsvynarchuk.persistence.dao.RoleDao;
import com.gmail.maxsvynarchuk.persistence.dao.SubscriptionPlanDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.EntityMapper;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.RoleMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.SubscriptionPlan;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SubscriptionPlanMySqlDao implements SubscriptionPlanDao {
    private final static String SELECT_ALL =
            "SELECT * FROM roles ";

    private final static String INSERT =
            "INSERT INTO roles (role_name) VALUES(?);";

    private final static String UPDATE =
            "UPDATE roles SET role_name = ? ";

    private final static String DELETE =
            "DELETE FROM roles ";

    private final static String WHERE_ID =
            "WHERE role_id = ? ";


    private final UtilMySqlDao<Role> utilMySqlDao;

    public SubscriptionPlanMySqlDao() {
        this(new RoleMapper());
    }

    public SubscriptionPlanMySqlDao(EntityMapper<Role> mapper) {
        this(new UtilMySqlDao<>(mapper));
    }

    public SubscriptionPlanMySqlDao(UtilMySqlDao<Role> utilMySqlDao) {
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

    public List<Role> findAll(int skip, int limit) {
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
}
