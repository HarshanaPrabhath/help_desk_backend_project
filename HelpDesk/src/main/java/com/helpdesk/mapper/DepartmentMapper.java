package com.helpdesk.mapper;

import com.helpdesk.Model.department.Department;
import com.helpdesk.Model.user.User;
import com.helpdesk.dto.DepartmentDTO;

import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department) {
        if (department == null) {
            return null;
        }

        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .users(department.getUsers().stream().map(User::getUserId).collect(Collectors.toList()))
                .build();


    }

    public static Department toEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return null;
        }
        return Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .build();
    }
}
