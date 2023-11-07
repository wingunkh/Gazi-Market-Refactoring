package capstone.capstone.controller;

import capstone.capstone.domain.ChattingMessage;
import capstone.capstone.dto.ChattingMessageResponse;
import capstone.capstone.service.ChattingRoomService;
import capstone.capstone.service.ChattingService;
import capstone.capstone.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketChatController {
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChattingService chattingService;

    @Autowired
    private UserMemberService userMemberService;

    // 채팅 전송
    @MessageMapping("/chattingMessage/sendMessage")
    public void sendMessage(@Payload ChattingMessage chattingMessage) {
        ChattingMessage message = chattingService.sendMessage(chattingMessage);
        String senderProfileImage = userMemberService.showProfileImage(message.getSenderNum());
        String senderNickname = userMemberService.getNickName(message.getSenderNum());
        ChattingMessageResponse chattingMessageResponse = new ChattingMessageResponse(message, senderProfileImage, senderNickname);

        System.out.println(chattingMessageResponse.getRoomNum() + "번 채팅방 ->" + chattingMessageResponse.getSenderNickname() + ": " + chattingMessageResponse.getContent());
        template.convertAndSend("/sub/chattingMessage/room/" + chattingMessageResponse.getRoomNum(), chattingMessageResponse);
    }
}