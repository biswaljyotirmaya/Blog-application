package com.jb.blog.service;

import com.jb.blog.DTO.PostData;
import com.jb.blog.entity.Post;
import com.jb.blog.entity.User;
import com.jb.blog.repo.IPostsRepo;
import com.jb.blog.repo.IUsersRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    private final IPostsRepo postsRepo;

    private final IUsersRepo usersRepo;

    private final HttpSession httpSession;

    @Autowired
    public PostServiceImpl(IPostsRepo postsRepo, IUsersRepo usersRepo, HttpSession httpSession) {
        this.postsRepo = postsRepo;
        this.usersRepo = usersRepo;
        this.httpSession = httpSession;
    }

    @Override
    public String addPost(PostData postData) {
        User user = (User) httpSession.getAttribute("loggedInUser");

        if (user == null) {
            return "ERROR: User not logged in";
        }

        Post post = new Post();
        BeanUtils.copyProperties(postData, post);
        post.setUser(user);
        postsRepo.save(post);

        return "SUCCESS: Blog created successfully";
    }

    @Override
    public List<PostData> findAllPost() {
        List<Post> list = postsRepo.findAll();

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        List<PostData> result = new ArrayList<>();
        for (Post post : list) {
            PostData postData = new PostData();
            BeanUtils.copyProperties(post, postData);
            postData.setAuthorName(post.getUser().getFirstName());
            result.add(postData);
        }

        return result;
    }

    @Override
    public String editPost(PostData post) {
        return "";
    }

    @Override
    public String deletePost(PostData post) {
        return "";
    }

    @Override
    public String deleteAllPost() {
        return "";
    }

    @Override
    public List<PostData> findAllPostByUser(Long id) {
        User user = usersRepo.findUserById(id);

        if (user == null) {
            return new ArrayList<>();
        }

        List<PostData> result = new ArrayList<>();
        List<Post> posts = user.getPost();

        for (Post post : posts) {
            PostData postData = new PostData();
            BeanUtils.copyProperties(post, postData);
            result.add(postData);
        }

        return result;
    }

    @Override
    public void updatePost(PostData data, User user) {
        Post post = postsRepo.findById(data.getId()).orElse(null);

        if (post == null) {
            return;
        }

        if (!post.getUser().getId().equals(user.getId())) {
            return;
        }

        post.setTitle(data.getTitle());
        post.setDescription(data.getDescription());
        post.setContent(data.getContent());
        postsRepo.save(post);
    }

    @Override
    public boolean deletePost(Long id, User user) {
        Post post = postsRepo.findById(id).orElse(null);

        if (post == null) {
            return false;
        }

        if (!post.getUser().getId().equals(user.getId())) {
            return false;
        }

        postsRepo.delete(post);
        return true;
    }
}
