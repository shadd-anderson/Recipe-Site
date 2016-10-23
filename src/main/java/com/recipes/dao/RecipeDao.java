package com.recipes.dao;

import com.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RecipeDao extends CrudRepository<Recipe, Long> {
}
