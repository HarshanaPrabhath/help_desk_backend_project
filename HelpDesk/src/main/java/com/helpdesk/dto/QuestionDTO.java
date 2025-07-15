package com.helpdesk.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private Long questionID;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private boolean isAnonymous;
    private double vote;
    private Long userID;
    private Long categoryID;
    private String userName;
    private List<Long> answers;
}
