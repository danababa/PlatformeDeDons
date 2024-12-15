package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uca.m2.pdd.Model.dto.NotificationDto;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour gérer les notifications.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Crée une nouvelle notification.
     * @param notificationDto DTO contenant userId, message, type et relatedId
     * @return La notification créée sous forme de NotificationDto
     */
    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotification = notificationService.createNotification(
                notificationDto.getUserId(),
                notificationDto.getMessage(),
                notificationDto.getType(),
                notificationDto.getRelatedId()
        );
        return ResponseEntity.ok(createdNotification);
    }

    /**
     * Récupère toutes les notifications d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Liste de NotificationDto
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByUser(@PathVariable UUID userId) {
        List<NotificationDto> notifications = notificationService.getNotificationsByUser(userId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Récupère toutes les notifications non lues d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Liste de NotificationDto non lues
     */
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationDto>> getUnreadNotifications(@PathVariable UUID userId) {
        List<NotificationDto> unreadNotifications = notificationService.getUnreadNotificationsByUser(userId);
        return ResponseEntity.ok(unreadNotifications);
    }

    /**
     * Marque une notification comme lue.
     * @param notificationId ID de la notification à marquer comme lue
     * @return La notification mise à jour sous forme de NotificationDto
     */
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<NotificationDto> markNotificationAsRead(@PathVariable UUID notificationId) {
        NotificationDto updatedNotification = notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(updatedNotification);
    }

    /*@GetMapping("/notifications")
    public String showNotifications() {
        return "notifications"; // notifications.html
    }*/
}
