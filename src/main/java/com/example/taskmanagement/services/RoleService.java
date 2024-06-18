package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.RoleDto;
import com.example.taskmanagement.models.Role;

import java.util.List;

public interface RoleService {
    RoleDto addRole(Role role);

    RoleDto updateRole(Role role, Integer id);

    RoleDto getRoleById(Integer id);

    List<RoleDto> getAllRoles();

    void deleteRoleById(Integer id);
}