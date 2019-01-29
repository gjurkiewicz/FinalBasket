package com.jurkiewicz.grzegorz.FinalBasket.service.impl;

import com.jurkiewicz.grzegorz.FinalBasket.model.Role;
import com.jurkiewicz.grzegorz.FinalBasket.model.User;
import com.jurkiewicz.grzegorz.FinalBasket.repository.RoleRepository;
import com.jurkiewicz.grzegorz.FinalBasket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    public User getActiveUser(String name) {
        List<User> getUsers = userRepository.findAll();
        for (User u : getUsers) {
            if (u.getEmail().equals(name)) {
                return u;
            }
        }
        return null;
    }

}
