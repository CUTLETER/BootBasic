package com.simple.basic.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component // service, repository처럼 bean으로 등록됨
public class SocketHandler extends TextWebSocketHandler {
    // 소켓 핸들러 클래스는 기본적으로 네 가지를 상속 받음
    // 웹소켓 설정 추가 (config)
    
    // 소켓 연결된 사람들을 저장하는 1개의 채팅방
    // 다중 채팅방을 구현하고 싶다면 다른 객체에서 채팅방 관리할 수 있는 자료구조를 만들어야 함
    private Map<WebSocketSession, String> map = new HashMap<>();

    // handshake 이후에 연결이 성립되면 실행됨
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = "제이지";
        URI uri = session.getUri();

        if (uri != null) {
            String query = uri.getQuery(); // 쿼리스트링 얻음 userId=홍길동&room=1
            String param = query.split("&")[0]; // userId=홍길동
            String room = query.split("&")[1]; // room=1

            if(param.contains("userId=")) {
                userId = param.split("userId=")[1]; // userId 얻어냄
            }
            if(room.contains("room=")) {
                System.out.println("room 값은 알아서");
            }

        }

        System.out.println("요청 URI : "+uri);
        System.out.println("접속 세션 아이디 : "+session.getId());
        System.out.println("유저명 : "+userId);
        
        map.put(session, userId);

        // 입장 메세지
        broadcastMessage(userId+"님이 접속하셨습니다.");
    }

    // 현재 연결되어 있는 socket에 메세지를 전송할 때 실행됨
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("메세지 전송 중");
        //System.out.println(message.getPayload());
        //System.out.println("메세지를 보내는 세션 : "+session);
        System.out.println("현재 접속자 수 : "+map.size());
        broadcastMessage(map.get(session)+ "> " +message.getPayload());
    }

    // 연결이 끊기면 실행됨
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        broadcastMessage(map.get(session)+"님이 도망가셨습니다.");
        map.remove(session); // 세션 삭제
        System.out.println("연결 해제 : "+session.getId());
    }

    // 혹시나 소켓에서 에러가 발생하면 실행됨
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("소켓 에러 발생");
    }

    // 메세지 보내기 기능
    public void broadcastMessage(String msg) throws Exception{
        // 메세지를 map 안에 연결이 되어 있는 모든 사용자한테 보내야 함
        Set<Map.Entry<WebSocketSession, String>> set = map.entrySet(); // map 반복 돌리기
        for (Map.Entry<WebSocketSession, String> entry : set) {
            //entry.getValue() // userId 얻어짐
            WebSocketSession session = entry.getKey();

            if (session.isOpen()) {
                session.sendMessage(new TextMessage(msg));
            }
        }
    }
}
