package com.techmatrix18.service;

import com.techmatrix18.model.Permission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface PermissionService {

    List<Permission> getAllPermissions();
    Optional<Permission> getPermissionById(Long id);
    Permission createPermission(Permission permission);
    Permission updatePermission(Permission permission);
    void deletePermission(Long id);
}
