package com.recipes.web.controller;

import com.recipes.dao.UserRepository;
import com.recipes.model.Category;
import com.recipes.model.Recipe;
import com.recipes.model.User;
import com.recipes.service.CategoryService;
import com.recipes.service.IngredientService;
import com.recipes.service.RecipeService;
import com.recipes.service.UserService;
import com.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.recipes.web.FlashMessage.Status.FAILURE;
import static com.recipes.web.FlashMessage.Status.SUCCESS;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private UserService users;

    @RequestMapping("/")
    public String redirectToRecipesPage() {
        return "redirect:/recipes";
    }

    @RequestMapping("/recipes")
    public String index(Model model) {
        User user = (User) model.asMap().get("currentUser");
        model.addAttribute("authenticated", user.isAdmin());
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("categories", allCategories);
        List<Recipe> allRecipes = recipeService.findAll();
        model.addAttribute("allRecipes", allRecipes);
        return "index";
    }

    @RequestMapping("/recipes/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipeService.findOne((long) id);
        model.addAttribute("recipe", recipe);
        return "detail";
    }

    @RequestMapping(path = "/recipes/{id}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {
        User user = (User) model.asMap().get("currentUser");
        Recipe recipe = recipeService.findOne((long) id);
        if(recipe.getCreatedBy() == user || user.isAdmin()) {
            recipe.getCreatedBy().removeCreatedRecipe(recipe);
            users.save(recipe.getCreatedBy());
            recipe.setCreatedBy(null);
            recipeService.delete((long) id);
            attributes.addFlashAttribute("flash", new FlashMessage("Recipe deleted successfully", SUCCESS));
        } else {
            attributes.addFlashAttribute("flash", new FlashMessage("You are not allowed to delete this recipe.", FAILURE));
        }
        return "redirect:/";
    }

    //TODO: user-admin
    @RequestMapping("/recipes/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipeService.findOne((long) id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("action", "/recipes/" + id + "/edit");
        model.addAttribute("redirect", "/recipes/" + id);
        model.addAttribute("categories", categoryService.findAll());
        return "edit";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "searchQuery", required = false) String searchQuery, @RequestParam(value = "category", required = false) String category, HttpServletRequest request, HttpServletResponse response) {
        List<Recipe> queriedRecipes = new ArrayList<>();
        if(searchQuery != null) {
            model.addAttribute("search", searchQuery);
            List<Recipe> namedRecipes = recipeService.findByDescriptionContaining(searchQuery);
            if(!category.equals("")) {
                List<Recipe> categorizedRecipes = recipeService.findByCategoryName(category);
                queriedRecipes = namedRecipes.stream().filter(categorizedRecipes::contains).collect(Collectors.toList());
            } else {
                queriedRecipes = namedRecipes;
            }
        }
        model.addAttribute("allRecipes", queriedRecipes);
        List<Category> allCategories = categoryService.findAll();
        model.addAttribute("categoryService", allCategories);
        return "index";
    }

    @RequestMapping("/recipes/add")
    public String addRecipePage(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("action", "/recipes/add");
        model.addAttribute("redirect", "/");
        model.addAttribute("categories", categoryService.findAll());
        return "edit";
    }

    @RequestMapping(path = "/recipes/add", method = RequestMethod.POST)
    public String addRecipe(Model model, Recipe recipe) {
        Category category = recipe.getCategory();
        if(categoryService.findByName(category.getName()) != null) {
            recipe.setCategory(categoryService.findByName(recipe.getCategory().getName()));
        } else {
            categoryService.save(category);
        }
        recipe.getIngredients().forEach(ingredient -> ingredientService.save(ingredient));
        if(recipe.getCreatedBy() == null) {
            User user = (User)model.asMap().get("currentUser");
            recipe.setCreatedBy(user);
            recipeService.save(recipe);
            users.save(user);
        } else {
            recipeService.save(recipe);
        }
        return "redirect:/recipes/" + recipe.getId();
    }

    @RequestMapping(path = "/recipes/{id}/edit", method = RequestMethod.POST)
    public String saveEditedRecipe(Recipe recipe) {
        Category category = recipe.getCategory();
        if(categoryService.findByName(category.getName()) != null) {
            recipe.setCategory(categoryService.findByName(category.getName()));
        } else {
            categoryService.save(category);
        }
        recipe.getIngredients().forEach(ingredient -> ingredientService.save(ingredient));
        recipe.getInstructions()
                .stream()
                .filter(instruction -> instruction.equals(""))
                .collect(Collectors.toList())
                .forEach(recipe::removeInstruction);
        recipeService.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

}
