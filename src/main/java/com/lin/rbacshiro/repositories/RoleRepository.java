package com.lin.rbacshiro.repositories;

import com.lin.rbacshiro.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByUserId(String id);
}
