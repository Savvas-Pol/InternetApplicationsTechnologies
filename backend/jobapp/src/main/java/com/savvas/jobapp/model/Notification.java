package com.savvas.jobapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private String content;

    @ManyToOne
    @JoinColumn(name="sender_id", nullable=false)
    private User sender_id;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable=false)
    private User receiver_id;
}
