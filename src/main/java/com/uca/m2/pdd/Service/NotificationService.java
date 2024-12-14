package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Model.entity.Notification;
import com.uca.m2.pdd.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uca.m2.pdd.Model.dto.NotificationDto;
import com.uca.m2.pdd.Mapper.NotificationMapper;
import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.UsersRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service pour gérer la logique métier liée aux notifications.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UsersRepository usersRepository; // Ajout du repository utilisateur

    /**
     * Crée une notification après vérification de l'utilisateur.
     * @param userId ID de l'utilisateur
     * @param message Contenu de la notification
     * @param type Type de la notification (ex: "Info")
     * @param relatedId Identifiant optionnel d'une ressource liée
     * @return La notification créée sous forme de NotificationDto
     * @throws IllegalArgumentException si l'utilisateur n'existe pas
     */
    public NotificationDto createNotification(UUID userId, String message, String type, UUID relatedId) {
        // Vérifiez si l'utilisateur existe
        boolean userExists = usersRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + userId);
        }
        // Créez la notification
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRelatedId(relatedId);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.toNotificationDto(savedNotification);
    }

    /**
     * Récupère toutes les notifications pour un utilisateur donné.
     * @param userId ID de l'utilisateur
     * @return Liste de NotificationDto
     */
    public List<NotificationDto> getNotificationsByUser(UUID userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère toutes les notifications non lues d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Liste de NotificationDto non lues
     */
    public List<NotificationDto> getUnreadNotificationsByUser(UUID userId) {
        List<Notification> unreadNotifications = notificationRepository.findAllByUserIdAndEstLuFalse(userId);
        return unreadNotifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }

    /**
     * Marque une notification comme lue.
     * @param notificationId ID de la notification
     * @return La notification mise à jour sous forme de DTO
     * @throws IllegalArgumentException si la notification n'est pas trouvée
     */
    public NotificationDto markAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification non trouvée"));
        notification.setEstLu(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return NotificationMapper.toNotificationDto(updatedNotification);
    }
}
