package com.egorov.chatexample.services;

import com.egorov.chatexample.models.Message;
import com.egorov.chatexample.models.enums.MessageStatus;
import com.egorov.chatexample.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;

    public Message save(Message message){
        message.setMessageStatus(MessageStatus.RECEIVED);
        messageRepository.save(message);
        return message;
    }

    public Long countNewMessages(Long senderId, Long recipientId){
        return messageRepository.countBySenderIdAndRecipientIdAndAndMessageStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<Message> findByMessages(Long senderId, Long recipientId){

        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages = chatId.map(cId -> messageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatus(senderId, recipientId, MessageStatus.DELIVERED);
        }
        return messages;
    }

    public Message findById(Long id){
        return messageRepository.findById(id).
                map(message -> {
                    message.setMessageStatus(MessageStatus.DELIVERED);
                    return messageRepository.save(message);
        }).orElseThrow(()-> new RuntimeException("can't find message (" + id + ")"));
    }

    public void updateStatus(Long senderId, Long recipientId, MessageStatus status){
        Message message = messageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        message.setMessageStatus(status);
        messageRepository.save(message);
    }
}
