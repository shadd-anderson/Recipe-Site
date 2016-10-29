package com.recipes.web.controller;

import com.recipes.dao.CategoryRepository;
import com.recipes.dao.RecipeRepository;
import com.recipes.model.Category;
import com.recipes.model.Recipe;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(path = "/recipes/{id}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable("id") int id) {
        recipes.delete((long) id);
        return "redirect:/";
    }

    @RequestMapping("/recipes/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Recipe recipe = recipes.findOne((long) id);
        model.addAttribute("recipe", recipe);
        return "edit";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam(value = "searchQuery", required = false) String searchQuery, @RequestParam(value = "category", required = false) String category, HttpServletRequest request, HttpServletResponse response) {
        List<Recipe> queriedRecipes = new ArrayList<>();
        if(searchQuery != null) {
            model.addAttribute("search", searchQuery);
            List<Recipe> namedRecipes = recipes.findByNameContaining(searchQuery);
            if(!category.equals("")) {
                List<Recipe> categorizedRecipes = recipes.findByCategoryName(category);
                queriedRecipes = namedRecipes.stream().filter(categorizedRecipes::contains).collect(Collectors.toList());
            } else {
                queriedRecipes = namedRecipes;
            }
        }
        model.addAttribute("allRecipes", queriedRecipes);
        List<Category> allCategories = (List<Category>) categories.findAll();
        model.addAttribute("categories", allCategories);
        return "index";
    }

    @RequestMapping("/recipes/add")
    public String addRecipe() {
        return "edit";
    }
}
