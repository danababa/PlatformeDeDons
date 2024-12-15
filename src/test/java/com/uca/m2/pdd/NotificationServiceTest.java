package com.uca.m2.pdd;

import com.uca.m2.pdd.Model.dto.NotificationDto;
import com.uca.m2.pdd.Model.entity.Notification;
import com.uca.m2.pdd.Repository.NotificationRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import com.uca.m2.pdd.Service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void testCreateNotification_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String message = "Notification message";
        String type = "Info";
        UUID relatedId = UUID.randomUUID();

        Mockito.when(usersRepository.existsById(userId)).thenReturn(true);

        Notification savedNotification = new Notification();
        savedNotification.setNotificationId(UUID.randomUUID());
        savedNotification.setUserId(userId);
        savedNotification.setMessage(message);
        savedNotification.setType(type);
        savedNotification.setRelatedId(relatedId);
        savedNotification.setEstLu(false);
        savedNotification.setCreeA(LocalDateTime.now());

        Mockito.when(notificationRepository.save(Mockito.any())).thenReturn(savedNotification);

        // Act
        NotificationDto result = notificationService.createNotification(userId, message, type, relatedId);

        // Assert
        Assertions.assertNotNull(result.getNotificationId(), "L'ID de la notification doit être généré");
        Assertions.assertEquals(userId, result.getUserId(), "L'ID utilisateur doit correspondre");
        Assertions.assertEquals(message, result.getMessage(), "Le message doit correspondre");
        Assertions.assertEquals(type, result.getType(), "Le type doit correspondre");
        Assertions.assertFalse(result.isEstLu(), "La notification doit être non lue par défaut");
    }

    @Test
    void testCreateNotification_UserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String message = "Notification message";
        String type = "Info";
        UUID relatedId = UUID.randomUUID();

        Mockito.when(usersRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> notificationService.createNotification(userId, message, type, relatedId),
                "Une exception doit être levée si l'utilisateur n'existe pas");
    }

    @Test
    void testGetNotificationsByUser_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Notification notification1 = new Notification();
        notification1.setNotificationId(UUID.randomUUID());
        notification1.setUserId(userId);
        notification1.setMessage("Message 1");

        Notification notification2 = new Notification();
        notification2.setNotificationId(UUID.randomUUID());
        notification2.setUserId(userId);
        notification2.setMessage("Message 2");

        Mockito.when(notificationRepository.findAllByUserId(userId)).thenReturn(List.of(notification1, notification2));

        // Act
        List<NotificationDto> result = notificationService.getNotificationsByUser(userId);

        // Assert
        Assertions.assertEquals(2, result.size(), "Le nombre de notifications doit correspondre");
        Assertions.assertEquals("Message 1", result.get(0).getMessage(), "Le premier message doit correspondre");
        Assertions.assertEquals("Message 2", result.get(1).getMessage(), "Le deuxième message doit correspondre");
    }

    @Test
    void testGetUnreadNotificationsByUser_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Notification unreadNotification = new Notification();
        unreadNotification.setNotificationId(UUID.randomUUID());
        unreadNotification.setUserId(userId);
        unreadNotification.setMessage("Unread message");
        unreadNotification.setEstLu(false);

        Mockito.when(notificationRepository.findAllByUserIdAndEstLuFalse(userId)).thenReturn(List.of(unreadNotification));

        // Act
        List<NotificationDto> result = notificationService.getUnreadNotificationsByUser(userId);

        // Assert
        Assertions.assertEquals(1, result.size(), "Le nombre de notifications non lues doit correspondre");
        Assertions.assertEquals("Unread message", result.get(0).getMessage(), "Le message de la notification non lue doit correspondre");
        Assertions.assertFalse(result.get(0).isEstLu(), "La notification doit être non lue");
    }

    @Test
    void testMarkAsRead_Success() {
        // Arrange
        UUID notificationId = UUID.randomUUID();
        Notification notification = new Notification();
        notification.setNotificationId(notificationId);
        notification.setEstLu(false);

        Mockito.when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        Mockito.when(notificationRepository.save(Mockito.any())).thenReturn(notification);

        // Act
        NotificationDto result = notificationService.markAsRead(notificationId);

        // Assert
        Assertions.assertTrue(result.isEstLu(), "La notification doit être marquée comme lue");
        Mockito.verify(notificationRepository, Mockito.times(1)).save(notification);
    }

    @Test
    void testMarkAsRead_NotificationNotFound() {
        // Arrange
        UUID notificationId = UUID.randomUUID();
        Mockito.when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> notificationService.markAsRead(notificationId),
                "Une exception doit être levée si la notification n'est pas trouvée");
    }
}
