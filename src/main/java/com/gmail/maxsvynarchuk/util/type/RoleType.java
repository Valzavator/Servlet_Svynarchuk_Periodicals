package com.gmail.maxsvynarchuk.util.type;

import com.gmail.maxsvynarchuk.persistence.entity.Role;
import com.gmail.maxsvynarchuk.persistence.entity.User;

import java.util.Objects;

public enum RoleType {
    ADMIN(new Role(1, "admin")),
    USER(new Role(2, "user"));

    private final Role role;

    RoleType(Role role) {
        this.role = role;
    }

    public Role getValue() {
        return new Role(role.getId(), role.getName());
    }

    public int getId() {
        return role.getId();
    }

    public boolean isEquals(User user) {
        return Objects.nonNull(user) && role.equals(user.getRole());
    }
}
