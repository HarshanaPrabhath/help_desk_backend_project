package com.helpdesk.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long categoryId;
    private String categoryName;
    private List<QuestionDTO> questions;

}
