package com.recipes.service;

import com.recipes.dao.RecipeRepository;
import com.recipes.dao.UserRepository;
import com.recipes.model.Recipe;
import com.recipes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService users;

    @Override
    public boolean save(Recipe recipe, User user) {
        if(recipe.getCreatedBy() == user || user.isAdmin()) {
            recipeRepository.save(recipe);
            return true;
        } else {
            return false;
        }
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
    public boolean delete(Recipe recipe, User user) {
        if(recipe.getCreatedBy() == user || user.isAdmin()) {
            recipe.getCreatedBy().removeCreatedRecipe(recipe);
            users.save(recipe.getCreatedBy());
            recipe.setCreatedBy(null);
            recipeRepository.delete(recipe.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long id, User user) {
        return delete(recipeRepository.findOne(id), user);
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
