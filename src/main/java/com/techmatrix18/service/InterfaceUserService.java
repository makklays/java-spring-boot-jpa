package com.techmatrix18.service;

import com.techmatrix18.model.User;
import java.util.List;

public interface InterfaceUserService {
    List<User> getAllUsers();
    User getUserById(long userId);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}

