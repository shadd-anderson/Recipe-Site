package com.recipes.web.controller;

import com.recipes.dao.CategoryRepository;
import com.recipes.dao.IngredientRepository;
import com.recipes.dao.RecipeRepository;
import com.recipes.dao.UserRepository;
import com.recipes.model.Category;
import com.recipes.model.Recipe;
import com.recipes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private UserRepository users;

    @RequestMapping("/")
    public String redirectToRecipesPage() {
        return "redirect:/recipes";
    }

    @RequestMapping("/recipes")
    public String index(Model model) {
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAll();
        model.addAttribute("allRecipes", allRecipes);
        return "index";
    }

    @RequestMapping("/recipes/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipeRepository.findOne((long) id);
        model.addAttribute("recipe", recipe);
        return "detail";
    }

    @RequestMapping(path = "/recipes/{id}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable("id") int id) {
        recipeRepository.delete((long) id);
        return "redirect:/";
    }

    @RequestMapping("/recipes/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipeRepository.findOne((long) id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("action", "/recipes/" + id + "/edit");
        model.addAttribute("redirect", "/recipes/" + id);
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "searchQuery", required = false) String searchQuery, @RequestParam(value = "category", required = false) String category, HttpServletRequest request, HttpServletResponse response) {
        List<Recipe> queriedRecipes = new ArrayList<>();
        if(searchQuery != null) {
            model.addAttribute("search", searchQuery);
            List<Recipe> namedRecipes = recipeRepository.findByNameContaining(searchQuery);
            if(!category.equals("")) {
                List<Recipe> categorizedRecipes = recipeRepository.findByCategoryName(category);
                queriedRecipes = namedRecipes.stream().filter(categorizedRecipes::contains).collect(Collectors.toList());
            } else {
                queriedRecipes = namedRecipes;
            }
        }
        model.addAttribute("allRecipes", queriedRecipes);
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categoryRepository", allCategories);
        return "index";
    }

    @RequestMapping("/recipes/add")
    public String addRecipePage(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("action", "/recipes/add");
        model.addAttribute("redirect", "/");
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }

    @RequestMapping(path = "/recipes/add", method = RequestMethod.POST)
    public String addRecipe(Model model, Recipe recipe) {
        Category category = recipe.getCategory();
        if(categoryRepository.findByName(category.getName()) != null) {
            recipe.setCategory(categoryRepository.findByName(recipe.getCategory().getName()));
        } else {
            categoryRepository.save(category);
        }
        recipe.getIngredients().forEach(ingredient -> ingredientRepository.save(ingredient));
        if(recipe.getCreatedBy() == null) {
            User user = (User)model.asMap().get("user");
            recipe.setCreatedBy(user);
            recipeRepository.save(recipe);
            users.save(user);
        } else {
            recipeRepository.save(recipe);
        }
        return "redirect:/recipes/" + recipe.getId();
    }

    @RequestMapping(path = "/recipes/{id}/edit", method = RequestMethod.POST)
    public String saveEditedRecipe(Model model, Recipe recipe) {
        recipe.setCategory(categoryRepository.findByName(recipe.getCategory().getName()));
        recipe.getIngredients().forEach(ingredient -> ingredientRepository.save(ingredient));
        recipeRepository.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }
}
