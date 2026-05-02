package com.jb.blog.controller;

import com.jb.blog.DTO.LoginData;
import com.jb.blog.DTO.UserData;
import com.jb.blog.constant.Constants;
import com.jb.blog.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final IUserService userService;

    private final HttpSession session;

    @Autowired
    public UserController(IUserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(RedirectAttributes redirectAttributes, UserData userData) {
        System.out.println(userData);
        String res = userService.addUser(userData);
        if (res.startsWith(Constants.ERROR_START)) {
            redirectAttributes.addFlashAttribute("error", res.substring(Constants.ERROR_START.length()));
            return "redirect:/register";
        } else {
            redirectAttributes.addFlashAttribute("success", res.substring(Constants.SUCCESS_START.length()));
            return Constants.REDIRECT_HOME;
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(RedirectAttributes redirectAttributes, LoginData loginData) {
        System.out.println(loginData);
        String st = userService.login(loginData);
        if (st.startsWith(Constants.ERROR_START)) {
            redirectAttributes.addFlashAttribute("error", st.substring(Constants.ERROR_START.length()));
            return Constants.REDIRECT_LOGIN;
        }
        redirectAttributes.addFlashAttribute("success", st.substring(Constants.SUCCESS_START.length()));
        return Constants.REDIRECT_DASHBOARD;
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        session.invalidate();
        return Constants.REDIRECT_HOME;
    }

}
