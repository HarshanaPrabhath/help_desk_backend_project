package com.helpdesk.Service;

import com.helpdesk.Model.department.Department;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.DepartmentRepo;
import com.helpdesk.dto.DepartmentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = modelMapper.map(dto, Department.class);
        Department saved = departmentRepo.save(department);
        return convertToDTO(saved);
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department dept = departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return convertToDTO(dept);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO dto) {
        Department existing = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setDepartmentName(dto.getDepartmentName());

        Department updated = departmentRepo.save(existing);
        return convertToDTO(updated);
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);
        dto.setUsers(department.getUsers() != null
                ? department.getUsers().stream().map(User::getUserId).collect(Collectors.toList())
                : null);
        return dto;
    }
}
