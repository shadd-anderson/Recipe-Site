package com.recipes.service;

import com.recipes.dao.IngredientRepository;
import com.recipes.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;

public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}