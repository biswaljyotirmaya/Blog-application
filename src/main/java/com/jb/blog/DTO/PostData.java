package com.jb.blog.DTO;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class PostData {

    private String title;
    private String description;
    private String content;
}
