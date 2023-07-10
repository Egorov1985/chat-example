package com.egorov.chatexample.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table (name = "chatRooms")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String chatId;

    private Long senderId;

    private Long recipientId;

    private Date creatDateChat;


    @OneToMany (mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @PrePersist
    private void init(){
        creatDateChat = new Date();
    }



}
