package com.techmatrix18.repository;

import com.techmatrix18.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);

    List<User> findByPositionId(Long positionId);
    List<User> findByFirstname(String firstname);
    List<User> findByLastname(String lastname);
    List<User> findByLastnameAndFirstname(String firstname, String lastname);

    User findByEmailAndPassword(String email, String password);

    List<User> findByEmail(String email);
    List<User> findByRoles(String role);

    //Page<User> findAll(Pageable pageable);
}

