package com.techmatrix18.service.impl;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService /*, UserDetailsService*/ {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    /*@Override
    public User getUserByUserRoleId(Long userRoleId) {
        return userRepository.findByUserRoles(userRoleId);
    }*/

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> u = userRepository.findById(userId);
        return u.orElse(null);
    }

    @Override
    public List<User> getUsersByPositionId(Long positionId) {
        return userRepository.findByPositionId(positionId);
    }

    @Override
    public boolean addUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        User u = userRepository.save(user);
        return u.getId() != null;
    }

    @Override
    public boolean updateUser(User user) {
        User u = userRepository.save(user);
        if (u.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.getById(userId);
            if (user.getId() != null) {
                userRepository.delete(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAll(pageable);
    }
}

