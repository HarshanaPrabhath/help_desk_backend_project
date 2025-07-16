package com.helpdesk.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String title;
    private String description;
    private LocalDateTime createdDate;

    private boolean anonymous;
    private double vote;
    private Long userId;
    private Long categoryId;
    private String userName;
    private List<AnswerDTO> answers;


    public boolean getAnonymous() {
        return anonymous;

    }


}
