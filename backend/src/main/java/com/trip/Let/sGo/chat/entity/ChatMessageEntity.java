package com.trip.Let.sGo.chat.entity;

import com.trip.Let.sGo.chat.enums.MessageType;
import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chat_message")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String roomId;

    @ManyToOne
    private UserEntity sender;

    private String message;

    @CreatedDate
    private LocalDateTime timestamp;

}
