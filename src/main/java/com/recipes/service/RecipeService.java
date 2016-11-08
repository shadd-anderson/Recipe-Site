package com.recipes.service;

import com.recipes.model.Recipe;
import com.recipes.model.User;

import java.util.List;

public interface RecipeService {
    boolean save(Recipe recipe, User user);
    List<Recipe> findAll();
    Recipe findOne(Long id);
    boolean delete(Long id, User user);
    boolean delete(Recipe recipe, User user);
    List<Recipe> findByDescriptionContaining(String searchQuery);
    List<Recipe> findByCategoryName(String category);
}
