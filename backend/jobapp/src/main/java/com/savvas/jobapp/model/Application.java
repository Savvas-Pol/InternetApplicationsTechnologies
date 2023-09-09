package com.savvas.jobapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String application_date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ad_id", nullable=false)
    private Ad ad_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Ad getAd_id() {
        return ad_id;
    }

    public void setAd_id(Ad ad_id) {
        this.ad_id = ad_id;
    }
}
