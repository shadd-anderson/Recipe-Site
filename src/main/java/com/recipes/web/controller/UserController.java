package com.recipes.web.controller;

import com.recipes.dao.UserRepository;
import com.recipes.model.User;
import com.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService users;

    @RequestMapping("/profile")
    public String profile(Model model) {
        User user = (User)model.asMap().get("currentUser");
        model.addAttribute("user", user);
        model.addAttribute("authenticated", true);
        return "profile";
    }

    @RequestMapping("/users/{id}")
    public String userProfile(@PathVariable("id") Long id, Model model) {
        User user = users.findOne(id);
        User currentUser = (User) model.asMap().get("currentUser");
        if(currentUser != null) {
            if (currentUser.isAdmin()) {
                model.addAttribute("authenticated", true);
            }
        }
        model.addAttribute("user", user);
        return "profile";
    }
}
