package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.entity.Notification;
import com.uca.m2.pdd.Model.dto.NotificationDto;

public class NotificationMapper {

    // Convertir une entité Notification en NotificationDto
    public static NotificationDto toNotificationDto(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationDto dto = new NotificationDto();
        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUserId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setRelatedId(notification.getRelatedId());
        dto.setEstLu(notification.isEstLu());
        dto.setCreeA(notification.getCreeA());
        return dto;
    }

    // Convertir un NotificationDto en une entité Notification
    public static Notification toNotificationEntity(NotificationDto dto) {
        if (dto == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setNotificationId(dto.getNotificationId());
        notification.setUserId(dto.getUserId());
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRelatedId(dto.getRelatedId());
        notification.setEstLu(dto.isEstLu());
        notification.setCreeA(dto.getCreeA());
        return notification;
    }
}
