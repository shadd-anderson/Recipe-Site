package com.recipes.service;

import com.recipes.dao.RecipeRepository;
import com.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe findOne(Long id) {
        return recipeRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        recipeRepository.delete(id);
    }

    @Override
    public List<Recipe> findByDescriptionContaining(String searchQuery) {
        return recipeRepository.findByDescriptionContaining(searchQuery);
    }

    @Override
    public List<Recipe> findByCategoryName(String category) {
        return recipeRepository.findByCategoryName(category);
    }
}
