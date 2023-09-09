package com.savvas.jobapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String details;
    private String authored_at;
    private String deleted_at;
    private String published_at;
    private String updated_at;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user_id;

    @ManyToMany
    @JoinTable(name = "user_views_ad",joinColumns = @JoinColumn(name = "ad_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> views = new ArrayList<>();

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="ad_id")
    private List<Application> application = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAuthored_at() {
        return authored_at;
    }

    public void setAuthored_at(String authored_at) {
        this.authored_at = authored_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public List<User> getViews() {
        return views;
    }

    public void setViews(List<User> views) {
        this.views = views;
    }

    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }
}
