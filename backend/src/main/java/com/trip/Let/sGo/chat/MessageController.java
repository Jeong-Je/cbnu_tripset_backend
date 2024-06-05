package com.trip.Let.sGo.chat;

import com.trip.Let.sGo.chat.dto.ChatMessageDTO;
import com.trip.Let.sGo.chat.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessageDTO chatMessageDTO) {
        if (MessageType.ENTER.equals(chatMessageDTO.getType())) {
            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 들어왔습니다.");
        }
        sendingOperations.convertAndSend("/sub/chatRoom/"+chatMessageDTO.getRoomId(), chatMessageDTO);

        this.chatService.saveMessage(chatMessageDTO);
    }
}
