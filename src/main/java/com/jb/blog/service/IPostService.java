package com.jb.blog.service;

import com.jb.blog.DTO.PostData;
import com.jb.blog.entity.User;

import java.util.List;

public interface IPostService {
    public String addPost(PostData post);

    public List<PostData> findAllPost();

    public String editPost(PostData post);

    public String deletePost(PostData post);

    public String deleteAllPost();

    public List<PostData> findAllPostByUser(Long id);

    public void updatePost(PostData data, User user);

    public boolean deletePost(Long id, User user) ;
}
