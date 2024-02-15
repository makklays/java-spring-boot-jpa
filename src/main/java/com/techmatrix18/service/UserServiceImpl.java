package com.techmatrix18.service;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements InterfaceUserService {
    @Lazy
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public User getUserById(long userId) {
        User obj = userRepository.findById(userId).get();
        return obj;
    }

    @Override
    public synchronized boolean addUser(User user) {
        //List<User> list = userRepository.findByFirstnameAndLastname(user.getFirstname(), user.getLastname());
        List<User> list = userRepository.findByEmail(user.getEmail());
        if (list.size() > 0) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.delete(getUserById(userId));
    }
}

