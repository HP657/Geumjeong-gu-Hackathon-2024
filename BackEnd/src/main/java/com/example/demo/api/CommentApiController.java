package com.example.demo.api;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Comments;
import com.example.demo.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 추가
    @PostMapping("/add/comment")
    public ResponseEntity<Response<String>> addComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        return commentService.addComment(commentDto, request).toResponseEntity();
    }

    // 댓글 삭제
    @DeleteMapping("/del/comment/{commentId}")
    public ResponseEntity<Response<String>> delComment(@PathVariable("commentId") Long commentId) {
        return commentService.delComment(commentId).toResponseEntity();
    }

    // 특정 게시물의 댓글 조회
    @GetMapping("/event/{eventId}")
    public ResponseEntity<Response<List<Comments>>> getCommentsByEventId(@PathVariable("eventId") Long eventId) {
        return commentService.getCommentsByEventId(eventId).toResponseEntity();
    }
}