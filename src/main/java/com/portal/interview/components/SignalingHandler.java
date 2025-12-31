/**
 * @author Krishna Alisetty
 * @date 23-12-2025
 */

package com.portal.interview.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SignalingHandler extends TextWebSocketHandler {

    private final Map<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {

        String room = (String) session.getAttributes().get("room");
        String role = (String) session.getAttributes().get("role");
        String userId = (String) session.getAttributes().get("userId");

        System.out.println("Room iD--" + room);
        // Safety check (should never fail)
        if (room == null) {
            session.close();
            return;
        }

        rooms.computeIfAbsent(room, r -> ConcurrentHashMap.newKeySet());
        rooms.get(room).add(session);
        for (WebSocketSession peer : rooms.get(room)) {
            if (!peer.getId().equals(session.getId())) {
                peer.sendMessage(message);
            }
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        rooms.values().forEach(ws -> ws.remove(session));
    }

    private void safeSend(WebSocketSession session, TextMessage message) {
        synchronized (session) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
