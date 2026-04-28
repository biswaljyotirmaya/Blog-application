package com.jb.blog.repo;

import com.jb.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostsRepo extends JpaRepository<Post, Long> {
}
