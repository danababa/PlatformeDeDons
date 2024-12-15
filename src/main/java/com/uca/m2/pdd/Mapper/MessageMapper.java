package com.uca.m2.pdd.Mapper;

import com.uca.m2.pdd.Model.dto.MessageDto;
import com.uca.m2.pdd.Model.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setTimestamp(message.getTimestamp());
        return dto;
    }

    public Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setId(dto.getId());
        // Sender and Receiver must be set manually in the service
        message.setContent(dto.getContent());
        message.setTimestamp(dto.getTimestamp());
        return message;
    }
}
