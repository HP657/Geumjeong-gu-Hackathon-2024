package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Events;
import com.example.demo.entity.Users;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.EventsRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventsRepository eventsRepository;


    public Response<String> addComment(CommentDto commentDto, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Events event = eventsRepository.findById(commentDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Event no found"));
        Comments comment = new Comments();
        comment.setUser(user);
        comment.setEvent(event);
        comment.setUsername(user.getUsername());
        comment.setCommentText(commentDto.getCommentText());
        commentRepository.save(comment);
        return new Response<>("댓글 달음", HttpStatus.OK);
    }

    @Transactional
    public Response<String> delComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return new Response<>("해당 댓글 삭제", HttpStatus.OK);
        } else {
            return new Response<>("해당 댓글을 찾을 수 없습니다", HttpStatus.NOT_FOUND);
        }
    }

    public Response<List<Comments>> getCommentsByEventId(Long eventId) {
        List<Comments> comments = commentRepository.findByEvent_EventId(eventId);
        return new Response<>(comments, HttpStatus.OK);
    }
}