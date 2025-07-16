package com.helpdesk.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {
    private Long batchId;
    private String batchName;
    private List<UserDTO> users;

}
