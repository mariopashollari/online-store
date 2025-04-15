package com.individual.online_store.controllers;


import com.individual.online_store.dtos.CategoryDto;
import com.individual.online_store.entities.Category;
import com.individual.online_store.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {return categoryService.create(category);}

    @GetMapping("/all")
    public List<CategoryDto> findAll() {return categoryService.findAll();}

    @PutMapping("/update")
    public CategoryDto update(@RequestBody CategoryDto category) {return categoryService.update(category);}


}
