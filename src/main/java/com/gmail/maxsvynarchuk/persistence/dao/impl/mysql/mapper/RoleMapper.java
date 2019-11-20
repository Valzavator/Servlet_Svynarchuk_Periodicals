package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements EntityMapper<Role> {
    private static final String ID_FIELD = "role_id";
    private static final String NAME_FIELD = "role_name";

    @Override
    public Role mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        int roleId = resultSet.getInt(tablePrefix + ID_FIELD);
        String roleName = resultSet.getString(tablePrefix + NAME_FIELD);
        return new Role(roleId, roleName);
    }
}
