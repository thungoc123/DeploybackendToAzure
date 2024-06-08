package com.example.SWP391.repository;

import com.example.SWP391.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    // Phương thức để lưu vai trò vào cơ sở dữ liệu nếu chưa tồn tại
    default Role saveIfNotExist(Role role) {
        Role existingRole = findByRoleName(role.getRoleName());
        if (existingRole == null) {
            return save(role);
        }
        return existingRole;
    }

}
