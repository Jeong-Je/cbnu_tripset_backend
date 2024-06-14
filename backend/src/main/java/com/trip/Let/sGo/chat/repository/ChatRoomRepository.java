package com.trip.Let.sGo.chat.repository;

import com.trip.Let.sGo.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {
    ChatRoomEntity findByRoomId(String roomId);
    List<ChatRoomEntity> findAll();
}
