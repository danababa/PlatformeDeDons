package com.uca.m2.pdd;

import com.uca.m2.pdd.Controller.MessageController;
import com.uca.m2.pdd.Model.dto.MessageDto;
import com.uca.m2.pdd.Service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageControllerTests {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    public MessageControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        UUID senderId = UUID.randomUUID();
        UUID receiverId = UUID.randomUUID();
        String content = "Hello, this is a test message.";

        MessageDto mockMessage = new MessageDto();
        mockMessage.setSenderId(senderId);
        mockMessage.setReceiverId(receiverId);
        mockMessage.setContent(content);

        when(messageService.sendMessage(senderId, receiverId, content)).thenReturn(mockMessage);

        MessageDto result = messageController.sendMessage(senderId, receiverId, content);

        assertNotNull(result);
        assertEquals(senderId, result.getSenderId());
        assertEquals(receiverId, result.getReceiverId());
        assertEquals(content, result.getContent());
        verify(messageService, times(1)).sendMessage(senderId, receiverId, content);
    }

    @Test
    void testGetConversation() {
        UUID user1Id = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();
        List<String> mockConversation = Arrays.asList("Hello!", "Hi!", "How are you?");

        when(messageService.getConversation(user1Id, user2Id)).thenReturn(mockConversation);

        List<String> result = messageController.getConversation(user1Id, user2Id);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains("Hello!"));
        assertTrue(result.contains("Hi!"));
        verify(messageService, times(1)).getConversation(user1Id, user2Id);
    }
}
