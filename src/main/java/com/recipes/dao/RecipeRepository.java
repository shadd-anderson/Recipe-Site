package com.recipes.dao;

import com.recipes.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @RestResource(rel = "description-contains", path = "containsDescription")
    List<Recipe> findByDescriptionContaining(@Param("description") String description);

    @RestResource(rel = "category", path = "category")
    List<Recipe> findByCategoryName(@Param("name") String name);
}
