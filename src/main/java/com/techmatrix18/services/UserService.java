package com.techmatrix18.services;

import com.techmatrix18.entities.User;
import com.techmatrix18.rep.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRep userRepository;

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

