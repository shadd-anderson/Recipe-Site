package com.recipes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private Map<String, Integer> ingredients;
    private List<String> instructions;
    private int prepTime;
    private int cookTime;
    @Lob
    private byte[] image;

    public Recipe() {}

    public Recipe(RecipeBuilder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.ingredients = builder.ingredients;
        this.instructions = builder.instructions;
        this.prepTime = builder.prepTime;
        this.cookTime = builder.cookTime;
        this.image = builder.image;
    }

    public static class RecipeBuilder {
        private int id;
        private String name;
        private String category;
        private Map<String, Integer> ingredients;
        private List<String> instructions;
        private int prepTime;
        private int cookTime;
        private byte[] image;

        public RecipeBuilder(String name, String category) {
            this.name = name;
            this.category = category;
            ingredients = new HashMap<>();
            instructions = new ArrayList<>();
        }

        public RecipeBuilder setIngredients(Map<String, Integer> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeBuilder addIngredient(String ingredient, int measurement) {
            ingredients.put(ingredient, measurement);
            return this;
        }

        public RecipeBuilder setInstructions(List<String> instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeBuilder addInstruction(String instruction, int step) {
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

        public RecipeBuilder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient, int measurement) {
        ingredients.put(ingredient, measurement);
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public void addInstruction(String instruction, int step) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}