package capstone.capstone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// 웹 소켓 (Web Socket)이란? 단방향 통신을 지원하는 HTTP와는 달리 양방향 통신을 지원하는 프로토콜이다.
// HTTP 프로토콜을 통한 첫 번째 핸드셰이크를 주고받은 후 지속적으로 연결이 유지되는 것이 특징이다.
// STOMP (Simple Text Oriented Messaging Protocol)란?
// 클라이언트와 서버가 주고받을 메시지의 유형, 형식, 내용 등을 정의한 프로토콜이다. 웹 소켓 프로토콜과 함께 사용된다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // 웹 소켓 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("http://?").withSockJS();
    }

    // 메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 구독할 대상 설정
        registry.enableSimpleBroker("/sub");
        // 클라이언트가 발행한 메시지의 목적지 (prefix) 설정
        registry.setApplicationDestinationPrefixes("/api/chatting");
    }
}