package com.ProjectD.chatApplication.handler;

import lombok.Data;

@Data
public class ChatMessageDto {
    private String user;
    private String message;
    private String imageData;
    private String fileName;
    private String fileType;

    public ChatMessageDto() {}

    public ChatMessageDto(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public ChatMessageDto(String user, String message, String imageData, String fileName, String fileType) {
        this.user = user;
        this.message = message;
        this.imageData = imageData;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
