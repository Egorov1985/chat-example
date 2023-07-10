package com.egorov.chatexample.controllers;

import com.egorov.chatexample.models.ChatNotification;
import com.egorov.chatexample.models.Message;
import com.egorov.chatexample.services.ChatRoomService;
import com.egorov.chatexample.services.MessageService;
import com.egorov.chatexample.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message){
        var chatId = chatRoomService
                .getChatId(message.getSenderId(), message.getRecipientId(), true);
        message.setChatId(chatId.get());

        Message messageSave = messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getRecipientId()), "/queue/messages",
                new ChatNotification(messageSave.getId(),
                        messageSave.getSenderId(),
                        messageSave.getSenderName()));
    }

    @GetMapping ("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages (@PathVariable Long senderId,
                                                 @PathVariable Long recipientId){
        return ResponseEntity.ok(messageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping ("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages (@PathVariable Long senderId,
                                                  @PathVariable Long recipientId) {
        return ResponseEntity.ok(messageService.findByMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable Long id){
        return ResponseEntity.ok(messageService.findById(id));
    }


    @GetMapping("/chatRooms")
    public String chats(Model model) {
        model.addAttribute("chatRoomList", chatRoomService.allChats());
        return "chats";
    }

    @PostMapping("/createChatRoom")
    public String create(@RequestParam("chatRoomName") String chatName, Principal principal) {
        chatRoomService.createChatRoom(chatName, userService.findByUser(principal.getName()));
        return "redirect:/chatRooms";
    }

    @GetMapping ("/chatRoom/{id}")
    public String chatInfo(@PathVariable (name = "id") Long id, Model model){
        model.addAttribute("chatRoom", chatRoomService.findById(id));
        return "chat";
    }
}
