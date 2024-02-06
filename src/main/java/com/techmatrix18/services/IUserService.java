package com.techmatrix18.services;

import com.techmatrix18.entities.User;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(long userId);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}

