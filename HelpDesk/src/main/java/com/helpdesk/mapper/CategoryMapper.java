package com.helpdesk.mapper;

import com.helpdesk.Model.category.Category;
import com.helpdesk.Model.question.Question;
import com.helpdesk.dto.CategoryDTO;

import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .categoryID(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .question(category.getQuestions() != null
                         ? category.getQuestions().stream().map(Question::getQuestionID).collect(Collectors.toList())
                        :null)
                .build();
    }

    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return Category.builder()
                .categoryId(dto.getCategoryID())
                .categoryName(dto.getCategoryName())
                .build();
    }

}
