package com.jb.blog.service;

import com.jb.blog.DTO.PostData;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Override
    public String addPost(PostData post) {
        return "";
    }

    @Override
    public List<PostData> findAllPost() {
        return List.of();
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
