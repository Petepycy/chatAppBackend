package com.ProjectD.chatApplication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
//        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String messagePayload = message.getPayload();
        Map<String, Object> messageMap = objectMapper.readValue(messagePayload, Map.class);

        // Extract the necessary fields
        String user = (String) messageMap.get("user");
        String messageText = (String) messageMap.get("message");
        String imageData = (String) messageMap.get("imageData");
        String fileName = (String) messageMap.get("fileName");
        String fileType = (String) messageMap.get("fileType");

        // Create a new message object with the extracted fields
        ChatMessageDto chatMessageDto = new ChatMessageDto(user, messageText, imageData, fileName, fileType);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessageDto)));
        }
//        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
//        super.afterConnectionClosed(session, status);
    }
}
