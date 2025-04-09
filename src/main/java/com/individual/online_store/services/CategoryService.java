package com.individual.online_store.services;


import com.individual.online_store.dtos.CategoryDto;
import com.individual.online_store.entities.Category;
import com.individual.online_store.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::toDto)
                .toList();
    }

    public Category create(
            Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            return null;
        } else {
            category.setName(category.getName());
            return categoryRepository.save(category);
        }
    }

    public CategoryDto update(CategoryDto categoryDto) {
        if (categoryDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(categoryDto.getCategoryId())
                    .orElseThrow();
            category.setName(category.getName());
            return CategoryDto.toDto(category);
        } else {
            return null;
        }
    }



}
