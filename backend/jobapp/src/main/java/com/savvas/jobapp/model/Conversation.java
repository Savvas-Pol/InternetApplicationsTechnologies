package com.savvas.jobapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conversation")
@Data
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;

    @ManyToOne
    @JoinColumn(name="user1_id", nullable=false)
    private User user1_id;

    @ManyToOne
    @JoinColumn(name="user2_id", nullable=false)
    private User user2_id;
}
