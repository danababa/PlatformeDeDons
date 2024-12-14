package com.uca.m2.pdd.Repository;

import com.uca.m2.pdd.Model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // Trouver les notifications d'un utilisateur
    List<Notification> findAllByUserId(UUID userId);

    // Trouver les notifications non lues d'un utilisateur
    List<Notification> findAllByUserIdAndEstLuFalse(UUID userId);
}
