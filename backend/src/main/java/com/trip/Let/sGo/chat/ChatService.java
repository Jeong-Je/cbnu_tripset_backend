package com.trip.Let.sGo.chat;

import com.trip.Let.sGo.chat.dto.ChatMessageDTO;
import com.trip.Let.sGo.chat.dto.ChatRoomDTO;
import com.trip.Let.sGo.chat.entity.ChatMessageEntity;
import com.trip.Let.sGo.chat.entity.ChatRoomEntity;
import com.trip.Let.sGo.chat.repository.ChatMessageRepository;
import com.trip.Let.sGo.chat.repository.ChatRoomRepository;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;


    // 채팅방 리스트 불러오기
    public List<ChatRoomDTO> findAllRooms() {
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findAll();
        return chatRooms.stream()
                .map(room -> {
                    ChatRoomDTO dto = new ChatRoomDTO();
                    dto.setRoomId(room.getRoomId());
                    dto.setRoomName(room.getRoomName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 채팅방 하나 불러오기
    public ChatRoomDTO findRoomById(String roomId) {
        ChatRoomEntity roomEntity = chatRoomRepository.findByRoomId(roomId);

        if (roomEntity != null) {
            ChatRoomDTO roomDTO = new ChatRoomDTO();
            roomDTO.setRoomId(roomEntity.getRoomId());
            roomDTO.setRoomName(roomEntity.getRoomName());
            return roomDTO;
        }
        return null;
    }

    // 채팅방 생성
    public ChatRoomDTO createRoom(String name) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(name);

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRoomId(chatRoom.getRoomId());
        chatRoomEntity.setRoomName(chatRoom.getRoomName());

        chatRoomRepository.save(chatRoomEntity);
        return chatRoom;
    }

    // 채팅 내역 저장
    public void saveMessage(ChatMessageDTO chatMessageDTO) {
        UserEntity sender = this.userRepository.findByUsername(chatMessageDTO.getSender());

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setType(chatMessageDTO.getType());
        chatMessageEntity.setRoomId(chatMessageDTO.getRoomId());
        chatMessageEntity.setSender(sender);
        chatMessageEntity.setMessage(chatMessageDTO.getMessage());

        chatMessageRepository.save(chatMessageEntity);
    }

    // 채팅 내역 불러오기
    public List<ChatMessageDTO> getChatHistory(String roomId) {
        List<ChatMessageEntity> chatHistory = this.chatMessageRepository.findByRoomId(roomId);
        UserEntity userEntity;
        String sender;
        return chatHistory.stream()
                .map(chats -> {
                    ChatMessageDTO dto = new ChatMessageDTO();
                    dto.setMessage(chats.getMessage());
                    dto.setSender(chats.getSender().getUsername());
                    dto.setType(chats.getType());
                    dto.setRoomId(chats.getRoomId());
                    dto.setTimestamp(chats.getTimestamp());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
