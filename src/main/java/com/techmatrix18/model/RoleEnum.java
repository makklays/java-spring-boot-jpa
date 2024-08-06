package com.techmatrix18.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple JavaBean domain that represents a Role
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public enum RoleEnum {
    USER(Set.of(PermissionEnum.USER_READ)),
    ADMIN(Set.of(PermissionEnum.USER_READ, PermissionEnum.USER_WRITE));

    private final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

