package com.jb.blog.service;

import com.jb.blog.DTO.PostData;

import java.util.List;

public interface IPostService {
    public String addPost(PostData post);

    public List<PostData> findAllPost();

    public String editPost(PostData post);

    public String deletePost(PostData post);

    public String deleteAllPost();
}
