package com.trip.Let.sGo.chat;

import com.trip.Let.sGo.chat.dto.ChatRoomDTO;
import com.trip.Let.sGo.chat.entity.ChatRoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDTO> getAllRooms() {
        return this.chatService.findAllRooms();
    }

    // 새로운 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDTO createRoom(@RequestParam("name") String name) {
        return this.chatService.createRoom(name);
    }

    // 채팅방 하나 불러오기
    @GetMapping("/room/{roomId}")
    public ChatRoomDTO getRoomById(@PathVariable("roomId") String roomId) {
        return chatService.findRoomById(roomId);
    }
}
