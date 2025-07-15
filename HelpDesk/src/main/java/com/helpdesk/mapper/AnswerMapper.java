package com.helpdesk.mapper;

import com.helpdesk.Model.answer.Answer;
import com.helpdesk.dto.AnswerDTO;

public class AnswerMapper {
    public static AnswerDTO toDTO(Answer answer) {
        if (answer == null) {
            return null;
        }
        return AnswerDTO.builder()
                .answerID(answer.getAnswerId())
                .description(answer.getDescription())
                .createdAt(answer.getCreatedDate())
                .vote(answer.getVote())
                .questionID(answer.getQuestion()!=null
                ? answer.getQuestion().getQuestionID() : null)
                .build();

    }

    public static Answer toEntity(AnswerDTO answerDTO) {
        if (answerDTO == null) {
            return null;
        }
        return Answer.builder()
                .answerId(answerDTO.getAnswerID())
                .description(answerDTO.getDescription())
                .createdDate(answerDTO.getCreatedAt())
                .vote(answerDTO.getVote())
                .build();

    }
}
