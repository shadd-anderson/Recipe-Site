package com.recipes.web.controller;

import com.recipes.dao.CategoryRepository;
import com.recipes.dao.RecipeRepository;
import com.recipes.model.Category;
import com.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RecipeController {
    @Autowired
    private RecipeRepository recipes;
    @Autowired
    private CategoryRepository categories;

    @RequestMapping("/")
    public String index(Model model) {
        List<Category> allCategories = (List<Category>) categories.findAll();
        model.addAttribute("categories", allCategories);
        List<Recipe> allRecipes = (List<Recipe>) recipes.findAll();
        model.addAttribute("allRecipes", allRecipes);
        return "index";
    }

    @RequestMapping("/recipes/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipes.findOne((long) id);
        model.addAttribute("recipe", recipe);
        return "detail";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "edit";
    }
}
