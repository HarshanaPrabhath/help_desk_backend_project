package com.helpdesk.Service;

import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.question.Question;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.AnswerRepo;
import com.helpdesk.Repository.QuestionRepo;
import com.helpdesk.Repository.UserRepo;
import com.helpdesk.dto.AnswerDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    public AnswerDTO createAnswer(AnswerDTO dto) {
        Answer answer = modelMapper.map(dto, Answer.class);
        if(dto.getUserID() != null) {
            User user =  userRepo.findById(dto.getUserID())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            answer.setUser(user);
        }
        if(dto.getQuestionID() != null) {
            Question question = questionRepo.findById(dto.getQuestionID())
                            .orElseThrow(() -> new EntityNotFoundException("Question not found"));
            answer.setQuestion(question);
        }

        Answer savedAnswer = answerRepo.save(answer);
        return convertToDTO(savedAnswer);
    }

    public AnswerDTO updateAnswer(AnswerDTO dto) {
        Answer exist = answerRepo.findById(dto.getAnswerID())
                        .orElseThrow(()-> new EntityNotFoundException("Answer not found"));

        exist.setDescription(dto.getDescription());
        Answer savedAnswer = answerRepo.save(exist);
        return convertToDTO(savedAnswer);
    }

    public List<AnswerDTO> findAllAnswer() {
        return answerRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AnswerDTO findAnswerById(Long id) {
        return convertToDTO(answerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Answer not found!")));
    }
    public void deleteAnswer(Long id) {
        answerRepo.deleteById(id);
    }

    private AnswerDTO convertToDTO(Answer answer) {
        AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);

        answerDTO.setQuestionID(answer.getQuestion() != null ? answer.getQuestion().getQuestionID() : null);
        answerDTO.setUserID(answer.getUser() != null ? answer.getUser().getUserId() : null);

        return answerDTO;


    }
}
