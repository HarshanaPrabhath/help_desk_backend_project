package com.helpdesk.Service;

import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.category.Category;
import com.helpdesk.Model.question.Question;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.CategoryRepo;
import com.helpdesk.Repository.QuestionRepo;
import com.helpdesk.Repository.UserRepo;
import com.helpdesk.dto.QuestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public QuestionDTO createQuestion(QuestionDTO dto) {
        Question question = modelMapper.map(dto, Question.class);

        if (dto.getUserID() != null) {
            User user = userRepo.findById(dto.getUserID())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            question.setUser(user);
        }

        if (dto.getCategoryID() != null) {
            Category category = categoryRepo.findById(dto.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            question.setCategory(category);
        }

        Question saved = questionRepo.save(question);
        return convertToDTO(saved);
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO findById(Long id) {
        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return convertToDTO(question);
    }

    public QuestionDTO updateQuestion(QuestionDTO dto) {
        Question existing = questionRepo.findById(dto.getQuestionID())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setAnonymous(dto.isAnonymous());
        existing.setVote(dto.getVote());

        if (dto.getCategoryID() != null) {
            Category category = categoryRepo.findById(dto.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }

        Question updated = questionRepo.save(existing);
        return convertToDTO(updated);
    }


    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO dto = modelMapper.map(question, QuestionDTO.class);

        dto.setUserID(question.getUser() != null ? question.getUser().getUserId() : null);
        dto.setCategoryID(question.getCategory() != null ? question.getCategory().getCategoryId() : null);
        dto.setAnswers(question.getAnswers() != null
                ? question.getAnswers().stream().map(Answer::getAnswerId).collect(Collectors.toList())
                : null);

        dto.setUserName(question.isAnonymous() ? "Anonymous" :
                question.getUser()!=null ? question.getUser().getFirstName()+" "+question.getUser().getLastName() : null);



        return dto;
    }
}
