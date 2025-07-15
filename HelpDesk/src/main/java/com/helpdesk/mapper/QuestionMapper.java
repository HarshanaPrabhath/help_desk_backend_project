package com.helpdesk.mapper;

import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.question.Question;
import com.helpdesk.dto.QuestionDTO;

import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question  question) {
        if(question == null) return null;

        return QuestionDTO.builder()
                .questionID(question.getQuestionID())
                .title(question.getTitle())
                .description(question.getDescription())
                .vote(question.getVote())
                .createdDate(question.getCreatedDate())
                .isAnonymous(question.isAnonymous())
                .categoryID(question.getCategory()!=null
                ? question.getCategory().getCategoryId():null)
                .userID(question.getUser()!=null?question.getUser().getUserId():null)
                .answers(question.getAnswers()!=null
                ? question.getAnswers().stream().map(Answer::getAnswerId).collect(Collectors.toList())
                : null)
                .build();
    }

    public static Question toEntity(QuestionDTO questionDTO) {
        if(questionDTO == null) return null;

        return Question.builder()
                .questionID(questionDTO.getQuestionID())
                .title(questionDTO.getTitle())
                .isAnonymous(questionDTO.isAnonymous())
                .vote(questionDTO.getVote())
                .description(questionDTO.getDescription())
                .createdDate(questionDTO.getCreatedDate())
                .build();

    }
}
