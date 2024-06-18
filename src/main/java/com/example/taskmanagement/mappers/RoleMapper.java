package com.example.taskmanagement.mappers;

import com.example.taskmanagement.dtos.RoleDto;
import com.example.taskmanagement.models.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;

    public Role toEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    public RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }
}