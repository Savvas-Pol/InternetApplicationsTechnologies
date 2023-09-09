package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ArticleCreateRequest;
import com.savvas.jobapp.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ArticleMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    public List<ArticleResponse> fromEntity(List<Article> articles) {
        List<ArticleResponse> data = new ArrayList<>();
        for (Article article : articles) {
            data.add(fromEntity(article));
        }
        return data;
    }

    public ArticleResponse fromEntity(Article article) {
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setId(article.getId());
        articleResponse.setTitle(article.getTitle());
        articleResponse.setContent(article.getContent());
        articleResponse.setAuthored_at(article.getAuthored_at());
        articleResponse.setDeleted_at(article.getDeleted_at());
        articleResponse.setPublished_at(article.getPublished_at());
        articleResponse.setUpdated_at(article.getUpdated_at());

        for (Comment com : article.getComment()) {
            CommentResponse cr = commentMapper.fromEntity(com);
            articleResponse.getComment().add(cr);
        }

        UserResponse u = userMapper.fromEntity(article.getUser_id());
        articleResponse.setUser_id(u);

        for (User entity : article.getLikes()) {
            UserResponse r = userMapper.fromEntity(entity);
            articleResponse.getUsers_liked().add(r);
        }

        return articleResponse;
    }

    public Article toEntity(ArticleCreateRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        DateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date now = new Date();
        article.setAuthored_at(formatter.format(now));

        Optional<User> user = userRepository.findById(request.getUser_id());

        article.setUser_id(user.get());

        return article;
    }
}
