package com.egorov.chatexample.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OutputMessage {

    private String author;
    private String content;
    private String createDate;
}
