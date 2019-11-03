package com.gmail.maxsvynarchuk;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.*;
import com.gmail.maxsvynarchuk.persistence.dao.impl.mysql.mapper.*;
import com.gmail.maxsvynarchuk.persistence.entity.*;

import java.math.BigDecimal;
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

//        RoleDao dao = new RoleMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new RoleMapper()));
//
//        Role obj = new Role(2, "TEST");

//        AddressDao dao = new AddressMySqlDao(new UtilMySqlDao<>(() -> {
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

//        UserDao dao = new UserMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new UserMapper()));
//
//        User obj = User.newBuilder()
//                .setRole(new Role(1, null))
//                .setAddress(Address.newBuilder()
//                        .setCountry("UKRAINE")
//                        .setCity("ФІВФІВЇЇЇ")
//                        .setPostIndex("000")
//                        .setDetailAddress("ЫЫЫЪЪ")
//                        .build())
//                .setFirstName("ФЫВФЫВФЫВШІІІЇЇЇЇЇ")
//                .setLastName("LAST")
//                .setEmail("EMAI123s45L")
//                .setPassword("PASS")
//                .setDateOfBirth(new Date())
//                .setGender(User.Gender.FEMALE)
//                .build();

//        FrequencyDao dao = new FrequencyMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new FrequencyMapper()));
//
//        Frequency obj = Frequency.newBuilder()
//                .setName("TEST")
//                .setMeaning("UKRAINE")
//                .build();

//        PaymentDao dao = new PaymentMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new PaymentMapper()));
//
//        Payment obj = Payment.newBuilder()
//                .setUser(User.newBuilder().setId(1L).build())
//                .setTotalPrice(new BigDecimal(23))
//                .build();

//        SubscriptionPlanDao dao = new SubscriptionPlanMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new SubscriptionPlanMapper()));
//
//        SubscriptionPlan obj = SubscriptionPlan.newBuilder()
//                .setName("setName")
//                .setDescription("setDescription")
//                .setRate(new BigDecimal("0.22"))
//                .setMonthsAmount(12)
//                .build();

//        SubscriptionDao dao = new SubscriptionMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new SubscriptionMapper()));
//
//        Subscription obj = Subscription.newBuilder()
//                .setStartDate(new Date(10000000))
//                .setEndDate(new Date(100000000))
//                .setUser(User.newBuilder().setId(1L).build())
//                .setPeriodical(Periodical.newBuilder().setId(1L).build())
//                .setPayment(Payment.newBuilder().setId(1L).build())
//                .setSubscriptionPlan(SubscriptionPlan.newBuilder().setId(1).build())
//                .build();

//        PublisherDao dao = new PublisherMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new PublisherMapper()));
//
//        Publisher obj = new Publisher(2L, "TEST");

//        PeriodicalDao dao = new PeriodicalMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new PeriodicalMapper()));
//
//        Periodical obj = Periodical.newBuilder()
//                .setPublisher(new Publisher(1L, null))
//                .setFrequency(Frequency.newBuilder().setId(1).build())
//                .setPeriodicalType(PeriodicalType.newBuilder().setId(1).build())
//                .setName("setName")
//                .setPrice(new BigDecimal(12))
//                .setDescription("setDescription")
//                .build();
//

//        PeriodicalTypeDao dao = new PeriodicalTypeMySqlDao(new UtilMySqlDao<>(() -> {
//            try {
//                return DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        }, new PeriodicalTypeMapper()));
//
//        PeriodicalType obj = PeriodicalType.newBuilder()
//                .setName("Test")
//                .setDescription("setDescription")
//                .build();

        PeriodicalIssueDao dao = new PeriodicalIssueMySqlDao(new UtilMySqlDao<>(() -> {
            try {
                return DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }, new PeriodicalIssueMapper()));

        PeriodicalIssue obj = PeriodicalIssue.newBuilder()
                .setPeriodical(Periodical.newBuilder().setId(1L).build())
                .setName("setName")
                .setIssueNumber(3L)
                .setPublicationDate(new Date(100000000))
                .setDescription("setDescription")
                .build();

        System.out.println(dao.findAll(1, 3) + "\n");

        System.out.println(dao.findAll() + "\n");
        System.out.println(dao.insert(obj) + "\n");
        System.out.println(dao.findOne(obj.getId()) + "\n");
        obj.setName("NEW NAME");
        dao.update(obj);
        System.out.println(dao.findOne(obj.getId()) + "\n");
        dao.delete(obj.getId());
        System.out.println(dao.findOne(obj.getId()) + "\n");
        System.out.println(dao.findAll());
    }
}

