package com.techmatrix18.service.implementation;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService /*, UserDetailsService*/ {
    @Lazy
    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }*/

    /*@Override
    public String getPassword() {
        return null;
    }*/

    /*@Autowired
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //Set<Role> roles = new HashSet<>();
        user.setRoles("USER");
        userRepository.save(user);
    }*/

    @Override
    public List<User> findByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));

        System.out.println("== all users ===> " + list);

        if (list != null) {
            return list;
        } else {
            return null;
        }
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
        if (userRepository.existsById(userId)) {
            User usr = userRepository.getById(userId);
            userRepository.delete(usr);
            return true;
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

