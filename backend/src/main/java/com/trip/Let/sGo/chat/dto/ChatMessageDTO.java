package com.trip.Let.sGo.chat.dto;

import com.trip.Let.sGo.chat.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private MessageType type;

    private String roomId;

    private String sender;

    private String message;
}
