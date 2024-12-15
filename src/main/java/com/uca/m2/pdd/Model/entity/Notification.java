package com.uca.m2.pdd.Model.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue
    private UUID notificationId;  // Identifiant unique

    @Column(nullable = false)
    private UUID userId;          // Clé étrangère vers User

    @Column(nullable = false)
    private String message;       // Message de la notification

    @Column(nullable = false)
    private boolean estLu = false; // Statut : lu ou non

    @Column(nullable = false)
    private String type;          // Type de notification (Info, Alerte, etc.)

    @Column
    private UUID relatedId;       // Référence optionnelle vers un élément lié

    @Column(nullable = false)
    private java.time.LocalDateTime creeA = java.time.LocalDateTime.now(); // Date de création

    // Getters et Setters
    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEstLu() {
        return estLu;
    }

    public void setEstLu(boolean estLu) {
        this.estLu = estLu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(UUID relatedId) {
        this.relatedId = relatedId;
    }

    public java.time.LocalDateTime getCreeA() {
        return creeA;
    }

    public void setCreeA(java.time.LocalDateTime creeA) {
        this.creeA = creeA;
    }
}
