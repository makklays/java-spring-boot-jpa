package com.techmatrix18.repository;

import com.techmatrix18.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);

    Set<Permission> findAllById(Long id);
}
