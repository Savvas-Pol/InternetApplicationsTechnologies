package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.CommentMapper;
import com.savvas.jobapp.model.Comment;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.CommentRepository;
import com.savvas.jobapp.requests.CommentCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.CommentResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/all")
    public List<CommentResponse> all() {
        List<Comment> all = commentRepository.findAll();
        List<CommentResponse> result = commentMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public CommentResponse create(@RequestBody CommentCreateRequest request) {
        Comment entity = commentMapper.toEntity(request);

        Comment comment = commentRepository.saveAndFlush(entity);

        return commentMapper.fromEntity(comment);
    }

    @GetMapping("/id/{value}")
    public CommentResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Comment> comment = commentRepository.findById(value);

        if (comment.isPresent()) {
            return commentMapper.fromEntity(comment.get());
        } else {
            throw new ResourceNotFoundException("Comment", "id", value);
        }
    }
}
