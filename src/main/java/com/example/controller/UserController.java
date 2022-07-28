package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model){
        model.addAttribute("usersList", userService.getAll());
        return "users";
    }
    @GetMapping("/{id}")
    public String show (@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "show";
    }
    @GetMapping("/new")
    public String newPerson (Model model){
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", userService.add(user));
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
