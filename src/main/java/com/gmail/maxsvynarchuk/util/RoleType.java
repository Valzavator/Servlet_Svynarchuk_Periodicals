package com.gmail.maxsvynarchuk.util;

import com.gmail.maxsvynarchuk.persistence.entity.Role;

public enum RoleType {
    ADMIN(new Role(1,"admin")),
    USER(new Role(2,"user"));

    private final Role role;

    RoleType(Role role) {
        this.role = role;
    }

    public Role getValue() {
        return role;
    }

    public int getId() {
        return role.getId();
    }
}