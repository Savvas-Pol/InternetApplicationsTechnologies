package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Application;
import com.savvas.jobapp.model.UserLikesArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikesArticleRepository extends JpaRepository<UserLikesArticle, Integer> {

}
