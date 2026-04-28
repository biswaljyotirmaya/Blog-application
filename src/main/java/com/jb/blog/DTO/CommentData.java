package com.jb.blog.DTO;

import com.jb.blog.entity.Post;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class CommentData {
    private String name;
    private String email;
    private String content;
}
