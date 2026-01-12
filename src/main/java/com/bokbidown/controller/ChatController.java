package com.bokbidown.controller;

import com.bokbidown.Entity.ChatRoom;
import com.bokbidown.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/room")
    public String createRoom(@RequestParam Long propertyId){

        Long myId = 2L;
        ChatRoom room = chatService.createChatRoom(propertyId, myId);
        return "redirect:/chat/room/" + room.getId();
    }

    @GetMapping("/room/{roomId}")
    public String getChatRoom(@PathVariable Long roomId, Model model){
        ChatRoom room = chatService.getChatRoom(roomId);
        model.addAttribute("room", room);
        model.addAttribute("myId", 2L);
        return "view/chat_room";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestParam Long roomId,
                              @RequestParam String message) {
        Long senderId = 2L;
        chatService.sendMessage(roomId, senderId, message);
        return "redirect:/chat/room/" + roomId;
    }

}
