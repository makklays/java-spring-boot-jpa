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

    List<User> findByFirstname(String firstname);

    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Long id);

    Page<User> findPaginated(int pageNo, int pageSize);
}

