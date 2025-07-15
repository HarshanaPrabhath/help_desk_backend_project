package com.helpdesk.Service;

import com.helpdesk.Model.category.Category;
import com.helpdesk.Model.question.Question;
import com.helpdesk.Repository.CategoryRepo;
import com.helpdesk.dto.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category saved = categoryRepo.save(category);
        return convertToDTO(saved);
    }

    public List<CategoryDTO> findAllCategories() {
        return categoryRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToDTO(category);
    }

    public CategoryDTO updateCategory(CategoryDTO dto) {
        Category category = categoryRepo.findById(dto.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(dto.getCategoryName());
        Category updated = categoryRepo.save(category);

        return convertToDTO(updated);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);

        dto.setQuestion(
                category.getQuestions() != null
                        ? category.getQuestions().stream()
                        .map(Question::getQuestionID)
                        .collect(Collectors.toList())
                        : null
        );

        return dto;
    }
}
