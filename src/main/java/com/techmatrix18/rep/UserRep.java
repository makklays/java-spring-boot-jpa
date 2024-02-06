package com.techmatrix18.rep;

import com.techmatrix18.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// CrudRepository -> PagingAndSortingRepository -> JpaRepository
@Repository
public interface UserRep extends CrudRepository<User, Long> {
    //@PersistenceContext
    //EntityManager em;

    //User findByID(Long id);

    List<User> findByFirstnameAndLastname(String firstname, String lastname);
    List<User> findByEmail(String email);
    List<User> findByLastname(String lastname);

    Page<User> findAll(Pageable pageable);
}

