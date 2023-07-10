package com.egorov.chatexample.services;

import com.egorov.chatexample.models.ChatRoom;
import com.egorov.chatexample.models.User;
import com.egorov.chatexample.repositories.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom findById(Long id){
        return chatRoomRepository.findById(id).orElse(null);
    }

    public List<ChatRoom> allChats (){
        return chatRoomRepository.findAll();
    }

    public void createChatRoom(String chatName, User user){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setCreatDateChat(new Date());
        chatRoom.setChatId(chatName);
        chatRoom.setUser(user);
        chatRoomRepository.save(chatRoom);
    }

    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist){

        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(()-> {
                    if (createIfNotExist){
                        return Optional.empty();
                    }
                    var chaiId = String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chaiId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chaiId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chaiId);
                });
    }
}
