package com.bokbidown.service;

import com.bokbidown.Entity.ChatMessage;
import com.bokbidown.Entity.ChatRoom;
import com.bokbidown.domain.Property;
import com.bokbidown.domain.User;
import com.bokbidown.repository.ChatMessageRepository;
import com.bokbidown.repository.ChatRoomRepository;
import com.bokbidown.repository.PropertyRepository;
import com.bokbidown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public ChatRoom createChatRoom(Long propertyId, Long buyerId) {
        Property property = propertyRepository.findById(propertyId).
                orElseThrow(() -> new IllegalArgumentException("매물이 없습니다."));
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("구매자가 없습니다."));

        return chatRoomRepository.findByPropertyAndBuyer(property, buyer)
                .orElseGet(() -> {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setProperty(property);
                    chatRoom.setSeller(property.getSeller());
                    chatRoom.setBuyer(buyer);
                    chatRoom.setLastMessage("대화가 시작되었습니다.");
                    return chatRoomRepository.save(chatRoom);
                });
    }

    public void sendMessage(Long roomId,Long senderId, String messageContent) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 없습니다."));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setSender(sender);
        chatMessage.setMessage(messageContent);
        chatMessageRepository.save(chatMessage);
        chatRoom.setLastMessage(messageContent);
        chatRoom.setUpdatedAt(java.time.LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> getMyChatRooms(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        return chatRoomRepository.findBySellerOrBuyerOrderByUpdatedAtDesc(user, user);
    }

    @Transactional(readOnly = true)
    public ChatRoom getChatRoom(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 없습니다."));
    }

}
