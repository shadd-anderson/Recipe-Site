package com.recipes.dao;

import com.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
