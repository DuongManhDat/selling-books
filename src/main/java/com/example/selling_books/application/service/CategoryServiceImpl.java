package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.exception.BadRequestException;
import com.example.selling_books.application.model.dto.CategoryDTO;
import com.example.selling_books.application.model.request.CreateCategoryRequest;
import com.example.selling_books.application.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<CategoryDTO> getListCategories(PageRequest pageRequest) {
        Page<Category> categoryPage = categoryRepository.queryCategories(pageRequest);
        return categoryPage.map(category -> modelMapper.map(category,CategoryDTO.class));
    }

    @Override
    public CategoryDTO createCategory(CreateCategoryRequest createCategoryRequest) {
        Optional<Category> category = categoryRepository.findByName(createCategoryRequest.getName());
        if(category.isPresent()) {
           throw new BadRequestException("Thể loại này đã tồn tại");
        }
        Category newCategory = modelMapper.map(createCategoryRequest,Category.class);
        categoryRepository.save(newCategory);
        return modelMapper.map(newCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CreateCategoryRequest createCategoryRequest) {
        Optional<Category> category = categoryRepository.findById(id);
        Category newCategory = modelMapper.map(createCategoryRequest, Category.class);
        newCategory.setId(id);
        categoryRepository.save(newCategory);
        return modelMapper.map(newCategory,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getListCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
