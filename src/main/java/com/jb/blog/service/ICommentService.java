package com.jb.blog.service;

import com.jb.blog.DTO.CommentData;
import com.jb.blog.entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getComments(Long id);
    public void addComment(Long postId, CommentData data);
}
