package com.helpdesk.Service;



import com.helpdesk.Model.Batch;
import com.helpdesk.Model.Department;
import com.helpdesk.Model.User;
import com.helpdesk.Repository.BatchRepo;
import com.helpdesk.Repository.DepartmentRepo;
import com.helpdesk.Repository.UserRepo;
import com.helpdesk.dto.UserDTO;
import com.helpdesk.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final BatchRepo batchRepo;
    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        if (userDTO.getBatchId() != null) {
            Batch batch = batchRepo.findById(userDTO.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch ID not found"));
            user.setBatch(batch);
        }

        if (userDTO.getDepartmentId() != null) {
            Department department = departmentRepo.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department ID not found"));
            user.setDepartment(department);
        }

        User saved = userRepo.save(user);
        return userMapper.toDTO(saved);
    }

    public List<UserDTO> findAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    public List<UserDTO> findUserByFirstname(String name) {
        return userRepo.findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepo.findById(userDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setGender(userDTO.getGender());

        if (userDTO.getBatchId() != null) {
            Batch batch = batchRepo.findById(userDTO.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch ID not found"));
            existingUser.setBatch(batch);
        }

        if (userDTO.getDepartmentId() != null) {
            Department department = departmentRepo.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department ID not found"));
            existingUser.setDepartment(department);
        }

        User updated = userRepo.save(existingUser);
        return userMapper.toDTO(updated);
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }
}
