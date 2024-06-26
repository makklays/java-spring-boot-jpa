package com.techmatrix18.model;

/**
 * Simple JavaBean domain that represents a Permission
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    Permission (String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
