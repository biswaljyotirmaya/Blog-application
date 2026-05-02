package com.jb.blog.service;

import com.jb.blog.DTO.CommentData;
import com.jb.blog.entity.Comment;
import com.jb.blog.entity.Post;
import com.jb.blog.repo.ICommentsRepo;
import com.jb.blog.repo.IPostsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {


    private final ICommentsRepo commentRepo;

    private final IPostsRepo postRepo;

    @Autowired
    public CommentServiceImpl(ICommentsRepo commentRepo, IPostsRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public void addComment(Long postId, CommentData data) {

        Post post = postRepo.findById(postId).orElse(null);
        if (post == null) return;

        Comment comment = new Comment();

        comment.setName(data.getName());
        comment.setEmail(data.getEmail());
        comment.setContent(data.getContent());
        comment.setPost(post);

        commentRepo.save(comment);
    }

    public List<Comment> getComments(Long postId) {
        return commentRepo.findByPostId(postId);
    }
}
