package com.savvas.jobapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_views_ad")
public class UserViewsAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer ad_id;
    private Integer count;
}
