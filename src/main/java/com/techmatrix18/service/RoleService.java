package com.techmatrix18.service;

import com.techmatrix18.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();
    Role getRoleByName(String name);
    Optional<Role> getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Long id);
}
