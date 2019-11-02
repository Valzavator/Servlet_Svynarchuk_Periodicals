package com.gmail.maxsvynarchuk.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Maksym Svynarchuk
 * <p>
 * Class that represents user of system;
 * Each user must have one and only one role.
 * Each user may have at most one address.
 * @see Role
 * @see Address
 */
public class User implements Serializable {
    public enum Gender {
        MALE, FEMALE
    }

    private Long id;
    private Role role;
    private Address address;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Gender gender;

    public static class Builder {
        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder setId(Long id) {
            user.setId(id);
            return this;
        }

        public Builder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder setAddress(Address address) {
            user.setAddress(address);
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            user.setDateOfBirth(dateOfBirth);
            return this;
        }

        public Builder setGender(Gender gender) {
            user.setGender(gender);
            return this;
        }

        public User build() {
            return user;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("role=" + role)
                .add("address=" + address)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("gender=" + gender)
                .toString();
    }
}
