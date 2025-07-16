package com.helpdesk.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private String description;
    private LocalDateTime createdAt;
    private long vote;
    private Long userId;
    private Long questionId;

}
