package com.helpdesk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;


    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @NotBlank(message = "Last name can not be blank")
    private String lastName;

    @NotBlank(message = "Email can not be null")
    @Email(message = "Email Should be valid")
    private String email;

    @Size(min = 6,message = "Password should minimum 6 character")
    private String password;

    @NotBlank(message = "gender can not be blank")
    private String gender;
    private LocalDateTime createdAt;


    private List<Long> questionIds;
    private List<Long> answerIds;
    private List<Long> announcementIds;


    private Long batchId;
    private Long departmentId;

}
