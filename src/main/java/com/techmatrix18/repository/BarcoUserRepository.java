package com.techmatrix18.repository;

import com.techmatrix18.model.BarcoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcoUserRepository extends JpaRepository<BarcoUser, Long> {
    BarcoUser getById(Long id);
    List<BarcoUser> findByUserId(Long userId);
    List<BarcoUser> findByBarcoId(Long barcoId);

    Page<BarcoUser> findAll(Pageable pageable);
}

