package com.example.selling_books.application.service;

import com.example.selling_books.application.entity.Category;
import com.example.selling_books.application.model.dto.CategoryDTO;
import com.example.selling_books.application.model.request.CreateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDTO> getListCategories(PageRequest pageRequest);

    CategoryDTO createCategory(CreateCategoryRequest createCategoryRequest);

    CategoryDTO updateCategoryById(Long id, CreateCategoryRequest createCategoryRequest);

    void deleteCategoryById(Long id);
}
