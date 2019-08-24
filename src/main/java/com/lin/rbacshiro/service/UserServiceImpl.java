package com.lin.rbacshiro.service;

import com.lin.rbacshiro.dto.Role;
import com.lin.rbacshiro.dto.User;
import com.lin.rbacshiro.repositories.RoleRepository;
import com.lin.rbacshiro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> listRoleByUserId(String id) {
        return roleRepository.findByUserId(id);
    }
}
