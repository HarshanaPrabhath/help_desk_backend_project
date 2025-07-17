package com.helpdesk.Mapper;


import com.helpdesk.Model.Answer;
import com.helpdesk.DTO.AnswerDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "question.questionId",target = "questionId")
    @Mapping(source = "user.userId",target = "userId")
    AnswerDTO toDTO(Answer answer);


    @InheritInverseConfiguration
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    Answer toEntity(AnswerDTO answerDTO);
}
