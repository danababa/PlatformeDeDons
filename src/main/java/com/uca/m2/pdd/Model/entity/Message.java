package com.uca.m2.pdd.Model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Users receiver;

    @Column(name = "content",nullable = false, length = 500)
    private String content;

    @Column(name = "timestamp",nullable = false)
    private LocalDateTime timestamp;
}
