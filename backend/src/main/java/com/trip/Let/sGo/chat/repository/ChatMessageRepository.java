package com.trip.Let.sGo.chat.repository;

import com.trip.Let.sGo.chat.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Integer> {
}
