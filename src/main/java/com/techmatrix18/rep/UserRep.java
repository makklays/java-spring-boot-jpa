package com.techmatrix18.rep;

import com.techmatrix18.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface UserRep extends CrudRepository<User, Long> {
    //
}
