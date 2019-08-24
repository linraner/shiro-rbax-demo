package com.lin.rbacshiro.service;


import com.lin.rbacshiro.dto.Role;
import com.lin.rbacshiro.dto.User;

import java.util.List;

public interface IUserService {
    User getUserById(String id);

    List<Role> listRoleByUserId(String id);
}
