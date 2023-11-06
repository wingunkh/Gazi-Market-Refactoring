package capstone.capstone.controller;

import capstone.capstone.domain.ChattingMessage;
import capstone.capstone.extendedDomain.ChattingWithName;
import capstone.capstone.service.ChattingRoomService;
import capstone.capstone.service.ChattingService;
import capstone.capstone.service.UserMemberService;
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

    @Autowired
    private ChattingController chattingController;

    @Autowired
    private ChattingRoomService chattingRoomService;

    @Autowired
    private UserMemberService userMemberService;

    // 채팅 전송
    @MessageMapping("/chattingMessage/sendMessage")
    public void sendMessage(@Payload ChattingMessage chattingMessage) {
        ChattingMessage ch = chattingService.sendMessage(chattingMessage);
        ChattingWithName chatting_withName = new ChattingWithName(chattingMessage, userMemberService.getNickName(chattingMessage.getSenderNum()));
        chatting_withName.setCht_member_profile(userMemberService.showProfileImage(chatting_withName.getCht_member()));
        template.convertAndSend("/sub/chattingMessage/room/" + chattingMessage.getRoomNum(), chatting_withName);
        System.out.println(chatting_withName.getCht_room_num() + "번 채팅방 ->" + chatting_withName.getCht_member_name() + ": " + chatting_withName.getCht_text());
    }
}