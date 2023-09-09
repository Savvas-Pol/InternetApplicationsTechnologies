package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.ArticleRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.CommentCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ArticleResponse;
import com.savvas.jobapp.responses.CommentResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CommentMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleMapper articleMapper;

    public List<CommentResponse> fromEntity(List<Comment> comments) {
        List<CommentResponse> data = new ArrayList<>();
        for (Comment comment: comments) {
            data.add(fromEntity(comment));
        }
        return data;
    }

    public CommentResponse fromEntity(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        UserResponse u = userMapper.fromEntity(comment.getUser_id());
        commentResponse.setUser_id(u);

        commentResponse.setArticle_id(comment.getArticle_id().getId());

        return commentResponse;
    }

    public Comment toEntity(CommentCreateRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());

        Optional<User> user = userRepository.findById(request.getUser_id());
        comment.setUser_id(user.get());

        Optional<Article> article = articleRepository.findById(request.getArticle_id());
        comment.setArticle_id(article.get());

        return comment;
    }
}
