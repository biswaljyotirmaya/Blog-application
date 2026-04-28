package com.jb.blog.controller;

import com.jb.blog.DTO.CommentData;
import com.jb.blog.DTO.PostData;
import com.jb.blog.entity.Comment;
import com.jb.blog.entity.Post;
import com.jb.blog.entity.User;
import com.jb.blog.repo.IPostsRepo;
import com.jb.blog.service.ICommentService;
import com.jb.blog.service.IPostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostsRepo iPostsRepo;

    @GetMapping("/")
    public String home(Model model) {
        List<PostData> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<PostData> posts = postService.findAllPostByUser(user.getId());
        model.addAttribute("posts", posts);

        return "userDashboard";
    }

    @GetMapping("/post/new")
    public String createPostPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("postData", new PostData());
        model.addAttribute("title", "Create Blog");

        return "blog";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = iPostsRepo.findById(id).orElse(null);

        if (post == null) {
            return "redirect:/";
        }

        List<Comment> comments = commentService.getComments(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("commentData", new CommentData());

        return "read-post";
    }

    @PostMapping("/post/comment")
    public String addComment(@ModelAttribute CommentData commentData,
                             @RequestParam Long postId,
                             RedirectAttributes redirectAttributes) {
        commentService.addComment(postId, commentData);
        redirectAttributes.addFlashAttribute("success", "Comment added successfully");

        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Post post = iPostsRepo.findById(id).orElse(null);

        if (post == null) {
            return "redirect:/dashboard";
        }

        if (!post.getUser().getId().equals(user.getId())) {
            return "redirect:/dashboard";
        }

        PostData dto = new PostData();
        BeanUtils.copyProperties(post, dto);

        model.addAttribute("postData", dto);
        model.addAttribute("isEdit", true);

        return "blog";
    }

    @PostMapping("/post/save")
    public String savePost(@ModelAttribute PostData postData,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (postData.getId() != null) {
            postService.updatePost(postData, user);
            redirectAttributes.addFlashAttribute("success", "Blog updated successfully");
        } else {
            postService.addPost(postData);
            redirectAttributes.addFlashAttribute("success", "Blog created successfully");
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        boolean deleted = postService.deletePost(id, user);

        if (deleted) {
            redirectAttributes.addFlashAttribute("success", "Blog deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "You are not allowed to delete this post");
        }

        return "redirect:/dashboard";
    }
}
