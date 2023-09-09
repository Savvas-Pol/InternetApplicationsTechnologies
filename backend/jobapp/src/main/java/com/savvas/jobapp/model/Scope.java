package com.savvas.jobapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "scope")
@Data
public class Scope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
}
