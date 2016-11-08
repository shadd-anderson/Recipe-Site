package com.recipes.service;

import com.recipes.model.Category;

import java.util.List;

public interface CategoryService {
    Category findOne(Long id);
    Category findByName(String name);
    List<Category> findAll();
    void save(Category category);
}
