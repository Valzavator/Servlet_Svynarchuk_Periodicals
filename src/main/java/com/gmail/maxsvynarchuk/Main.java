package com.gmail.maxsvynarchuk;

import com.gmail.maxsvynarchuk.persistence.dao.UserDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.UtilMySqlDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.UserMySqlDao;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.UserMapper;
import com.gmail.maxsvynarchuk.persistence.entity.Address;
import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/periodicals";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "admin");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useTimezone", "true");
        prop.put("serverTimezone", "Europe/Kiev");
        prop.put("useLegacyDatetimeCode", "false");
        Connection cn = DriverManager.getConnection(url, prop);

//        RoleDao dao = new RoleMySqlDao(new DefaultMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new RoleMapper()));
//
//        Role obj = new Role(2, "TEST");

//        AddressDao dao = new AddressMySqlDao(new DefaultMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new AddressMapper()));
//
//        Address obj = Address.newBuilder()
//                .setCountry("UKRAINE")
//                .setCity("ФІВФІВЇЇЇ")
//                .setPostIndex("000")
//                .setDetailAddress("ЫЫЫЪЪ")
//                .build();

        UserDao dao = new UserMySqlDao(new UtilMySqlDao<>(() -> {
            try {
                return DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }, new UserMapper()));

        User obj = User.newBuilder()
                .setRole(new Role(1, null))
                .setAddress(Address.newBuilder()
                        .setCountry("UKRAINE")
                        .setCity("ФІВФІВЇЇЇ")
                        .setPostIndex("000")
                        .setDetailAddress("ЫЫЫЪЪ")
                        .build())
                .setFirstName("ФЫВФЫВФЫВШІІІЇЇЇЇЇ")
                .setLastName("LAST")
                .setEmail("EMAI123s45L")
                .setPassword("PASS")
                .setDateOfBirth(new Date())
                .setGender(User.Gender.FEMALE)
                .build();

        System.out.println(dao.findAll(0, 3) + "\n");
//        System.out.println(dao.findAll() + "\n");
//        System.out.println(dao.insert(obj) + "\n");
//        System.out.println(dao.findOne(obj.getId()) + "\n");
//        obj.setRole(new Role(2, null));
//        dao.update(obj);
//        System.out.println(dao.findOne(obj.getId()) + "\n");
//        dao.delete(obj.getId());
//        System.out.println(dao.findOne(obj.getId()) + "\n");
//        System.out.println(dao.findAll());
    }
}

