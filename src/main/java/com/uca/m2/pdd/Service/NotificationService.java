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

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UsersRepository usersRepository; // Ajout du repository utilisateur

    /**
     * Créer une notification après vérification de l'utilisateur
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

    public List<NotificationDto> getNotificationsByUser(UUID userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getUnreadNotificationsByUser(UUID userId) {
        List<Notification> unreadNotifications = notificationRepository.findAllByUserIdAndEstLuFalse(userId);
        return unreadNotifications.stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }

    public NotificationDto markAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification non trouvée"));
        notification.setEstLu(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return NotificationMapper.toNotificationDto(updatedNotification);
    }
}
