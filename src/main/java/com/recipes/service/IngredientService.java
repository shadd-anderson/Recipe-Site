package com.recipes.service;

import com.recipes.model.Ingredient;

import java.util.List;

public interface IngredientService {
    void save(Ingredient ingredient);
    List<Ingredient> findByName(String name);
}
