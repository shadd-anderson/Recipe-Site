package com.recipes.dao;

import com.recipes.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientDao extends CrudRepository<Ingredient, Long> {
}
