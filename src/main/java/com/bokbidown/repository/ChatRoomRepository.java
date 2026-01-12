package com.bokbidown.repository;

import com.bokbidown.Entity.ChatRoom;
import com.bokbidown.domain.Property;
import com.bokbidown.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findBySellerOrBuyerOrderByUpdatedAtDesc(User seller, User buyer);

    Optional<ChatRoom> findByPropertyAndBuyer(Property property, User buyer);
}
