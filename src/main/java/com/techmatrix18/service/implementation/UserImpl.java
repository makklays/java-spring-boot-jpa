package com.techmatrix18.service.implementation;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.InterfaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserImpl implements InterfaceUser {
    @Lazy
    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));

        return list;
    }

    @Override
    public User getUserById(Long userId) {
        User obj = userRepository.getById(userId);
        return obj;
    }

    @Override
    public synchronized boolean addUser(User user) {
        // check email
        List<User> list = userRepository.findByEmail(user.getEmail());
        if (list.size() > 0) {
            return false;
        } else {
            // add
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public synchronized boolean updateUser(User user) {
        User usr = userRepository.save(user);
        if (!usr.getEmail().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long userId) {
        User usr = userRepository.getById(userId);
        if (!usr.getEmail().isEmpty()) {
            userRepository.delete(usr);
            return true;
        } else {
            return false;
        }
    }
}

