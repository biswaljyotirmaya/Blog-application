package com.jb.blog.DTO;

import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostData {
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdDate;
    private String authorName;
}
