//package com.recipes.config;
//
//import com.recipes.Application;
//import com.recipes.dao.CategoryRepository;
//import com.recipes.dao.IngredientRepository;
//import com.recipes.dao.RecipeRepository;
//import com.recipes.dao.UserRepository;
//import com.recipes.model.Category;
//import com.recipes.model.Ingredient;
//import com.recipes.model.Recipe;
//import com.recipes.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//@Component
//public class DatabaseLoader implements ApplicationRunner {
//    private UserRepository users;
//    private IngredientRepository ingredients;
//    private RecipeRepository recipes;
//    private CategoryRepository categories;
//
//    @Autowired
//    public DatabaseLoader(UserRepository users, IngredientRepository ingredients, RecipeRepository recipes, CategoryRepository categories) {
//        this.users = users;
//        this.ingredients = ingredients;
//        this.recipes = recipes;
//        this.categories = categories;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        if(((List<User>)users.findAll()).isEmpty()) {
//            User admin = new User("admin", "password", new String[]{"ROLE_ADMIN", "ROLE_USER"});
//            User nonAdmin = new User("non-admin", "password", new String[]{"ROLE_USER"});
//            users.save(admin);
//            users.save(nonAdmin);
//            Ingredient ingredient = new Ingredient("pizza", "pie", 1);
//            ingredients.save(ingredient);
//            Category american = new Category("American");
//            Category italian = new Category("Italian");
//            categories.save(american);
//            categories.save(italian);
//            Recipe pizza = new Recipe.RecipeBuilder("Pizza", american)
//                    .addIngredient(ingredient)
//                    .setDescription("Mmm... Delicious pizza")
//                    .setCookTime(15)
//                    .setPrepTime(5)
//                    .addInstruction("Place pizza in oven", 0)
//                    .addInstruction("Cook pizza for 15 minutes", 1)
//                    .setImage("http://kingrichiespizza.com/wp-content/uploads/2015/12/d5a3498cfc9e53130b5f815ef44713b7_Jet.jpg")
//                    .build();
//            pizza.setCreatedBy(admin);
//            Recipe burger = new Recipe.RecipeBuilder("Burger", american).build();
//            Recipe spaghetti = new Recipe.RecipeBuilder("Spaghetti", italian).build();
//            recipes.save(pizza);
//            recipes.save(burger);
//            recipes.save(spaghetti);
//            users.save(admin);
//        } else {
//            Application.main(args.getSourceArgs());
//        }
//    }
//}