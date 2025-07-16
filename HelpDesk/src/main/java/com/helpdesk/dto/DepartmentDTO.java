package com.helpdesk.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private List<UserDTO> users;

}
