package com.recipes.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

public class RecipeTest {
    @Test
    public void recipeBuilderWorksProperly() throws Exception {
        Category category = new Category("category");
        Ingredient ingredient = new Ingredient("ingredient", "measurement", 5);
        Recipe recipe = new Recipe.RecipeBuilder("recipe", category)
                .addIngredient(ingredient)
                .addInstruction("instruction", 0)
                .setCookTime("5 minutes")
                .setPrepTime("10 minutes")
                .setDescription("description")
                .setImage("image")
                .build();

        assertThat("recipe category got set", recipe.getCategory().equals(category));
        assertThat("recipe name set correctly", recipe.getName().equals("recipe"));
        assertThat("recipe ingredient was added", recipe.getIngredients().size() == 1);
        assertThat("ingredient name is correct", recipe.getIngredients().get(0).getName().equals("ingredient"));
        assertThat("ingredient measurement is correct", recipe.getIngredients().get(0).getMeasurement().equals("measurement"));
        assertThat("ingredient amount is correct", recipe.getIngredients().get(0).getQuantity() == 5);
        assertThat("recipe has one instruction", recipe.getInstructions().size() == 1);
        assertThat("recipe cook time is correct", recipe.getCookTime().equals("5 minutes"));
        assertThat("recipe prep time is correct", recipe.getPrepTime().equals("10 minutes"));
        assertThat("recipe description is 'description'", recipe.getDescription().equals("description"));
        assertThat("recipe image URL set", recipe.getImageUrl().equals("image"));
        assertThat("recipe is not favorited by user", !recipe.isFavorited(user));
    }

    private User user = new User();
}
