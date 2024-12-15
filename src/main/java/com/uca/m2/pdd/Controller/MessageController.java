package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.MessageDto;
import com.uca.m2.pdd.Service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageDto sendMessage(@RequestParam UUID senderId, @RequestParam UUID receiverId, @RequestBody String content) {
        return messageService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping
    public List<String> getConversation(@RequestParam UUID user1Id, @RequestParam UUID user2Id) {
        return messageService.getConversation(user1Id, user2Id);
    }
}
