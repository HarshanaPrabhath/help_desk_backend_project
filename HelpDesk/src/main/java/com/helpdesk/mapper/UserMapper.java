package com.helpdesk.mapper;

import com.helpdesk.Model.announcemnt.Announcement;
import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.question.Question;
import com.helpdesk.Model.user.User;
import com.helpdesk.dto.UserDTO;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .gender(user.getGender())
                .createdAt(user.getCreatedAt())
                .questionIds(user.getQuestions() != null
                        ? user.getQuestions().stream().map(Question::getQuestionID).collect(Collectors.toList())
                        : null)
                .answerIds(user.getAnswers() != null
                        ? user.getAnswers().stream().map(Answer::getAnswerId).collect(Collectors.toList())
                        : null)
                .announcementIds(user.getAnnouncements() != null
                        ? user.getAnnouncements().stream().map(Announcement::getAnnouncementId).collect(Collectors.toList())
                        : null)
                .batchId(user.getBatch() != null ? user.getBatch().getBatchId() : null)
                .departmentId(user.getDepartment() != null ? user.getDepartment().getDepartmentId() : null)
                .build();
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = User.builder()
                .userId(dto.getUserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .createdAt(dto.getCreatedAt())
                .build();

        return user;
    }
}
