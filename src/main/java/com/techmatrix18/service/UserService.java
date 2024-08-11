package com.techmatrix18.service;

import com.techmatrix18.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.User}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);

    List<User> getUsersByPositionId(Long positionId);

    List<User> findByFirstname(String firstname);
    User getUserByEmailAndPassword(String email, String Password);

    //User getUserByUserRoleId(Long userRoleId);

    User getUserByEmail(String email);

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);

    Page<User> findPaginated(int pageNo, int pageSize);
}

