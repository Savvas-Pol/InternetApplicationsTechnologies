package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.UserLikesArticle;
import com.savvas.jobapp.model.UserViewsAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewsAdRepository extends JpaRepository<UserViewsAd, Integer> {

}
