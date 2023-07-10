package com.egorov.chatexample.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;
}
