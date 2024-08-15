package com.techmatrix18.repository;

import com.techmatrix18.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    //@EntityGraph(attributePaths = { "barcos", "roles" }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("""
            SELECT u FROM User u 
            LEFT JOIN FETCH u.roles
            LEFT join FETCH u.barcos
            WHERE u.email = :email
            """)
    User findByEmail(String email);
}

