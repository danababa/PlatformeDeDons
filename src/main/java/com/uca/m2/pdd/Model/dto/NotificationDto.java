package com.uca.m2.pdd.Model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationDto {
    private UUID notificationId;
    private UUID userId;
    private String message;
    private String type;
    private UUID relatedId;
    private boolean estLu;
    private LocalDateTime creeA;
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

    public boolean isEstLu() {
        return estLu;
    }

    public void setEstLu(boolean estLu) {
        this.estLu = estLu;
    }

    public LocalDateTime getCreeA() {
        return creeA;
    }

    public void setCreeA(LocalDateTime creeA) {
        this.creeA = creeA;
    }
}
