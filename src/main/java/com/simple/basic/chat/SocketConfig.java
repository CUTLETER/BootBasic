package com.simple.basic.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 스프링 설정 파일
@EnableWebSocket // 소켓 사용
public class SocketConfig implements WebSocketConfigurer {

    //@Autowired
    private SocketHandler socketHandler;

    // @Autowired 대신 생성자로 주입 받는 방식
    public SocketConfig(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/api/chat") // 요청 연결을 허용할 경로 - /api/chat/{example}/{ex} 이런 식으로 가변 매개변수 넣을 수도 있음
                .setAllowedOriginPatterns("*") // 서버가 다르더라도 허용함
                //.setAllowedOrigins("http://localhost:3000")
                .withSockJS(); // 소켓 js와 함께 들어감

        // 물론 registry.addHandler는 여러 개가 될 수도 있음
    }
}
