package com.jb.blog.controller;

import com.jb.blog.DTO.PostData;
import com.jb.blog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<PostData> posts= postService.findAllPost();
        model.addAttribute("posts",posts);
        return "home";
    }
}
