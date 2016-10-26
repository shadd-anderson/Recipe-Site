package com.recipes.config;

import com.recipes.dao.IngredientRepository;
import com.recipes.dao.RecipeRepository;
import com.recipes.dao.UserRepository;
import com.recipes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private UserRepository users;
    private IngredientRepository ingredients;
    private RecipeRepository recipes;

    @Autowired
    public DatabaseLoader(UserRepository users, IngredientRepository ingredients, RecipeRepository recipes) {
        this.users = users;
        this.ingredients = ingredients;
        this.recipes = recipes;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("admin", "password", new String[] {"ROLE_ADMIN", "ROLE_USER"});
        User nonAdmin = new User("non-admin", "password", new String[] {"ROLE_USER"});
        users.save(admin);
        users.save(nonAdmin);
    }
}