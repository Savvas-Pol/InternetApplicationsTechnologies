package com.savvas.jobapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "connection_request")
@Data
public class ConnectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user1_id", nullable=false)
    private User user1_id;

    @ManyToOne
    @JoinColumn(name="user2_id", nullable=false)
    private User user2_id;
}
