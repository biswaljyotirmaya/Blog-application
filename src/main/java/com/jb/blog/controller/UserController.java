package com.jb.blog.controller;

import com.jb.blog.DTO.LoginData;
import com.jb.blog.DTO.UserData;
import com.jb.blog.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(RedirectAttributes redirectAttributes, UserData userData) {
        System.out.println(userData);
        String res = userService.addUser(userData);
        if (res.startsWith("ERROR:")) {
            redirectAttributes.addFlashAttribute("error", res.substring("ERROR:".length()));
            return "redirect:/register";
        } else {
            redirectAttributes.addFlashAttribute("success", res.substring("SUCCESS:".length()));
            return "redirect:/";
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
        if (st.startsWith("ERROR:")) {
            redirectAttributes.addFlashAttribute("error", st.substring("ERROR:".length()));
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("success", st.substring("SUCCESS:".length()));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public void logout(Model model) {
        session.invalidate();
    }

}
