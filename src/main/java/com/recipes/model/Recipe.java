package com.recipes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends GenericEntity {
    private String name;
    private String description;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<Ingredient> ingredients;
    @ElementCollection
    private List<String> instructions;
    private int prepTime;
    private int cookTime;
    @ManyToOne
    private User createdBy;
    @OneToOne
    private Image image;

    public Recipe() {
        super();
        ingredients = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    public Recipe(RecipeBuilder builder) {
        this();
        this.name = builder.name;
        this.description = builder.description;
        this.category = builder.category;
        this.ingredients = builder.ingredients;
        this.instructions = builder.instructions;
        this.prepTime = builder.prepTime;
        this.cookTime = builder.cookTime;
        this.image = builder.image;
    }

    public static class RecipeBuilder {
        private String name;
        private String description;
        private Category category;
        private List<Ingredient> ingredients;
        private List<String> instructions;
        private int prepTime;
        private int cookTime;
        private Image image;

        public RecipeBuilder(String name, Category category) {
            this.name = name;
            this.category = category;
            ingredients = new ArrayList<>();
            instructions = new ArrayList<>();
        }

        public RecipeBuilder setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeBuilder addIngredient(Ingredient ingredient) {
            ingredients.add(ingredient);
            return this;
        }

        public RecipeBuilder addInstruction(String instruction, int step) {
            instruction = String.format("%d. %s", step + 1, instruction);
            instructions.add(step, instruction);
            return this;
        }

        public RecipeBuilder setPrepTime(int prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public RecipeBuilder setCookTime(int cookTime) {
            this.cookTime = cookTime;
            return this;
        }

        public RecipeBuilder setImage(Image image) {
            this.image = image;
            return this;
        }

        public RecipeBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void addInstruction(String instruction, int step) {
        instruction = String.format("%d. %s", step + 1, instruction);
        instructions.add(step, instruction);
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}