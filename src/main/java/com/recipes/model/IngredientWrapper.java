package com.recipes.model;

import java.util.List;

public class IngredientWrapper {
    private List<Ingredient> wrappedList;

    public IngredientWrapper() {}

    public IngredientWrapper(List<Ingredient> wrappedList) {
        this.wrappedList = wrappedList;
    }

    public List<Ingredient> getWrappedList() {
        return wrappedList;
    }

    public void setWrappedList(List<Ingredient> wrappedList) {
        this.wrappedList = wrappedList;
    }
}

