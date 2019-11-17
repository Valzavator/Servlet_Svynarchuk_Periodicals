package com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper;

import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.type.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements EntityMapper<User> {
    private static final String ID_FIELD = "user_id";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String DATE_OF_BIRTH_FIELD = "date_of_birth";
    private static final String GENDER_FIELD = "gender";

    private final EntityMapper<Role> roleMapper;

    public UserMapper() {
        this(new RoleMapper());
    }

    public UserMapper(EntityMapper<Role> roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public User mapToObject(ResultSet resultSet, String tablePrefix)
            throws SQLException {
        Role tempRole = roleMapper.mapToObject(resultSet);

        return User.newBuilder()
                .setId(resultSet.getLong(
                        tablePrefix + ID_FIELD))
                .setRole(tempRole)
                .setFirstName(resultSet.getString(
                        tablePrefix + FIRST_NAME_FIELD))
                .setLastName(resultSet.getString(
                        tablePrefix + LAST_NAME_FIELD))
                .setEmail(resultSet.getString(
                        tablePrefix + EMAIL_FIELD))
                .setPassword(resultSet.getString(
                        tablePrefix + PASSWORD_FIELD))
                .setDateOfBirth(resultSet.getObject(
                        tablePrefix + DATE_OF_BIRTH_FIELD, LocalDate.class))
                .setGender(Gender.valueOf(
                        resultSet.getString(tablePrefix + GENDER_FIELD).toUpperCase()))
                .build();
    }
}
