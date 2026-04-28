package com.jb.blog.controller;

import com.jb.blog.DTO.UserData;
import com.jb.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userData",new UserData());
        return "register";
    }
    @PostMapping("/register")
    public String addUser(Model model) {
        return "home";
    }

}
