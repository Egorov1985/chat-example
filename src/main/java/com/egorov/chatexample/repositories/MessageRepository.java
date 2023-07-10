package com.egorov.chatexample.repositories;

import com.egorov.chatexample.models.Message;
import com.egorov.chatexample.models.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Long> {
    Long countBySenderIdAndRecipientIdAndAndMessageStatus(Long senderId, Long recipientId, MessageStatus status);

    Message findBySenderIdAndRecipientId(Long senderId, Long recipientId);

   List<Message> findByChatId(String chatId);
}
