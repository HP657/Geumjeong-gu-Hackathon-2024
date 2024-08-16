package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long eventId;
    private String commentText;
}