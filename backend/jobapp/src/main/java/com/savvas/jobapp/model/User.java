package com.savvas.jobapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    @Column(nullable = false)
    private String password;
    private String phone;
    private String photo;
    private String description;

    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="scope_id_email", nullable=false)
    private Scope scope_id_email;

    @ManyToOne
    @JoinColumn(name="scope_id_phone", nullable=false)
    private Scope scope_id_phone;

    @ManyToOne
    @JoinColumn(name="scope_id_photo", nullable=false)
    private Scope scope_id_photo;

    @ManyToMany
    @JoinTable(name = "user_views_ad",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "ad_id"))
    private List<Ad> viewedAds;

    @ManyToMany
    @JoinTable(name = "user_likes_article",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> likedArticles;

    @ManyToMany
    @JoinTable(name = "connection_request",joinColumns = @JoinColumn(name = "user1_id"), inverseJoinColumns = @JoinColumn(name = "user2_id"))
    private List<User> connectedUsers;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<Experience> experience;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<Education> education;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<Skill> skill;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<Article> articles;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<Ad> ads;

}
