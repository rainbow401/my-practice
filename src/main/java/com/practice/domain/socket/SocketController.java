package com.practice.domain.socket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.TreeMap;

@ServerEndpoint("/ws/{userId}")
@Component
public class SocketController {

    private final String SOCKET_SESSION_PREFIX = "user-";

    private Map<String, Object> sessionPools = new TreeMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        String sessionKey = SOCKET_SESSION_PREFIX + userId;
        System.out.println(sessionKey);
        sessionPools.put(sessionKey, session);
        try {
            System.out.println("onOpen - { 欢迎" + sessionKey + " 加入连接！ sessionId: " + session.getId() + " }");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message ,Session session) {
        System.out.println("onMessage - { message:" + message + " }");
        for (int i = 0; i < this.sessionPools.size(); i++) {
            System.out.println("sessionPools: ");
        }

        for (Map.Entry<String, Object> e : sessionPools.entrySet()) {
            System.out.println("sessionPools-userId: " + e.getKey());
        }

    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        String sessionKey = SOCKET_SESSION_PREFIX + userId;
        sessionPools.remove(sessionKey);
        System.out.println("onClose - {   " + sessionKey + " 断开连接！ sessionId: " + session.getId() + " }");
    }

}