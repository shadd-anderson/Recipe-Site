package com.recipes.web.controller;

import com.recipes.dao.UserRepository;
import com.recipes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserRepository users;

    @RequestMapping("/profile")
    public String profile(Model model) {
        User user = (User)model.asMap().get("user");
        model.addAttribute("favoritedRecipes", user.getFavoritedRecipes());
        model.addAttribute("createdRecipes", user.getCreatedRecipes());
        return "profile";
    }
}
