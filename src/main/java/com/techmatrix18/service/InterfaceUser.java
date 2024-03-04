package com.techmatrix18.service;

import com.techmatrix18.model.*;
import java.util.List;

public interface InterfaceUser {
    List<User> getAllUsers();
    User getUserById(Long id);

    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
}

