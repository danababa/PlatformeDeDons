package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.MessageMapper;
import com.uca.m2.pdd.Model.dto.MessageDto;
import com.uca.m2.pdd.Model.entity.Message;
import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.MessageRepository;
import com.uca.m2.pdd.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UsersRepository usersRepository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, UsersRepository usersRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.usersRepository = usersRepository;
        this.messageMapper = messageMapper;
    }

    public MessageDto sendMessage(UUID senderId, UUID receiverId, String content) {
        Users sender = usersRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        Users receiver = usersRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageMapper.toDto(messageRepository.save(message));
    }

    public List<String> getConversation(UUID user1Id, UUID user2Id) {
        Users user1 = usersRepository.findById(user1Id)
                .orElseThrow(() -> new RuntimeException("User1 not found"));
        Users user2 = usersRepository.findById(user2Id)
                .orElseThrow(() -> new RuntimeException("User2 not found"));

        return messageRepository.findBySenderOrReceiver(user1, user2)
                .stream()
                .map(Message::getContent) // Extract only the content field
                .collect(Collectors.toList());
    }

}
