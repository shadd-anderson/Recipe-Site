package com.recipes.handler;

import com.recipes.dao.UserRepository;
import com.recipes.model.Recipe;
import com.recipes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Recipe.class)
public class RecipeHandler {
    private final UserRepository users;

    @Autowired
    public RecipeHandler(UserRepository users) {
        this.users=users;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void setCreatorOnCreate(Recipe recipe) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUsername(username);
        recipe.setCreatedBy(user);
    }
}