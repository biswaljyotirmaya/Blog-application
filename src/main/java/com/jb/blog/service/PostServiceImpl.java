package com.jb.blog.service;

import com.jb.blog.DTO.PostData;
import com.jb.blog.entity.Post;
import com.jb.blog.entity.User;
import com.jb.blog.repo.IPostsRepo;
import com.jb.blog.repo.IUsersRepo;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostsRepo postsRepo;

    @Override
    public String addPost(PostData post) {
        return "";
    }

    @Override
    public List<PostData> findAllPost() {
        List<Post> list = postsRepo.findAll();
        if (list.isEmpty()) {
            return null;
        }
        List<PostData> list1 = new ArrayList<>();
        for (Post post : list) {
            PostData postData = new PostData();
            BeanUtils.copyProperties(post, postData);
            list1.add(postData);
        }
        return list1;
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
}
