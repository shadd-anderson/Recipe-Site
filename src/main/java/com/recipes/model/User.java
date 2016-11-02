package com.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends GenericEntity {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String[] roles;
    @OneToMany
    private List<Recipe> createdRecipes;
    @ManyToMany
    private List<Recipe> favoritedRecipes;

    public User() {
        super();
        createdRecipes = new ArrayList<>();
        favoritedRecipes = new ArrayList<>();
    }

    public User(String username, String password, String[] roles) {
        this();
        this.username = username;
        this.roles = roles;
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    public List<Recipe> getFavoritedRecipes() {
        return favoritedRecipes;
    }

    public void setFavoritedRecipes(List<Recipe> favoritedRecipes) {
        this.favoritedRecipes = favoritedRecipes;
    }

    public void addCreatedRecipe(Recipe recipe) {
        createdRecipes.add(recipe);
    }

    public void addFavoritedRecipe(Recipe recipe) {
        favoritedRecipes.add(recipe);
    }
}
