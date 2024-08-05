package com.techmatrix18.config;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.info("don't have User");

            /*return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true, getAuthorities(user)); */
            throw new UsernameNotFoundException("Not found: " + email);
        } else {
            logger.info("have User "+user.toString());

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                    true, getAuthorities(user));
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String userRoles = user.getRoles();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}

