package capstone.capstone.controller;

import capstone.capstone.domain.Chatting;
import capstone.capstone.service.ChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class SocketChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChattingService chattingService;


    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload Chatting chat) {   //@payload, 전송되는 데이터
        System.out.println(chat.getCht_text());
        chat.setCht_time(LocalDateTime.now());
        chat.setCht_text(chat.getCht_text());
        Chatting ch = chattingService.createChatting(chat);
        System.out.println(ch.getCht_num());
        template.convertAndSend("/sub/chat/room/" + chat.getCht_room_num(), chat);
    }

}

