package com.helpdesk.Service;

import com.helpdesk.Model.announcemnt.Announcement;
import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.batch.Batch;
import com.helpdesk.Model.department.Department;
import com.helpdesk.Model.question.Question;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.BatchRepo;
import com.helpdesk.Repository.DepartmentRepo;
import com.helpdesk.Repository.UserRepo;
import com.helpdesk.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private BatchRepo batchRepo;
    @Autowired
    private ModelMapper modelMapper;


    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        if(userDTO.getBatchId() != null) {
            Batch batch = batchRepo.findById(userDTO.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch id not found"));
            user.setBatch(batch);

        }

        if(userDTO.getDepartmentId() != null) {
            Department department = departmentRepo.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department id not found"));
            user.setDepartment(department);
        }

        User saved = userRepo.save(user);
        return convertToDTO(saved);

    }

    public List<UserDTO> findAllUsers() {
        return userRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public List<UserDTO> findUserByFirstname(String name) {

        List<UserDTO> list = userRepo.findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepo.findById(userDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setGender(userDTO.getGender());

        if(userDTO.getBatchId() != null) {
            Batch batch = batchRepo.findById(userDTO.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch id not found"));
            existingUser.setBatch(batch);
        }

        if(userDTO.getDepartmentId() != null) {
            Department department = departmentRepo.findById(userDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department id not found"));
            existingUser.setDepartment(department);
        }
        User update = userRepo.save(existingUser);
        return convertToDTO(update);

    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }





    private UserDTO convertToDTO(User user) {
        UserDTO dto = modelMapper.map(user, UserDTO.class);

        dto.setQuestionIds(user.getQuestions() != null
                ? user.getQuestions().stream().map(Question::getQuestionID).collect(Collectors.toList())
                : null);

        dto.setAnswerIds(user.getAnswers() != null
                ? user.getAnswers().stream().map(Answer::getAnswerId).collect(Collectors.toList())
                : null);

        dto.setAnnouncementIds(user.getAnnouncements() != null
                ? user.getAnnouncements().stream().map(Announcement::getAnnouncementId).collect(Collectors.toList())
                : null);

        dto.setBatchId(user.getBatch() != null ? user.getBatch().getBatchId() : null);
        dto.setDepartmentId(user.getDepartment() != null ? user.getDepartment().getDepartmentId() : null);

        return dto;
    }


}
