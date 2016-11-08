package com.recipes.service;

import com.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {
    void save(Recipe recipe);
    List<Recipe> findAll();
    Recipe findOne(Long id);
    void delete(Long id);
    List<Recipe> findByDescriptionContaining(String searchQuery);
    List<Recipe> findByCategoryName(String category);
}
