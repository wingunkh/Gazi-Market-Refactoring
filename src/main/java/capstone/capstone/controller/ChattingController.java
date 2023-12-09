package capstone.capstone.controller;

import capstone.capstone.domain.ChattingMessage;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatting")
public class ChattingController {
    private final SimpMessagingTemplate template;

    private final ChattingMessageService chattingMessageService;

    private final ChattingRoomService chattingRoomService;

    // 채팅방 생성 후 입장
    @PostMapping("create/{postNum}/{guestNum}")
    public ResponseEntity<List<ChattingMessage>> createChattingRoom(@PathVariable Integer postNum, @PathVariable Integer guestNum) {
        Integer roomNum = chattingRoomService.enterChattingRoom(postNum, guestNum).getRoomNum();

        return ResponseEntity.ok(chattingMessageService.findAllByChattingRoomRoomNumOrderByTime(roomNum));
    }

    // 채팅 전송
    @PostMapping
    public ResponseEntity<ChattingMessage> sendHTTP(@RequestBody ChattingMessage chattingMessage) {
        return ResponseEntity.ok(chattingMessageService.send(chattingMessage));
    }

    // 채팅 전송
    @MessageMapping
    // 웹 소켓(Web Socket) 이란? HTTP와는 다른 통신 프로토콜로 웹 서버와 웹 브라우저가 서로 실시간 메시지를 교환하는 데 사용된다.
    // HTTP 프로토콜을 통한 첫 번째 핸드셰이크를 주고받은 후 지속적으로 연결이 유지되는 것이 특징이다.
    // @MessageMapping 어노테이션은 웹 소켓 엔드포인트에서 클라이언트로부터 메시지를 수신하는 핸들러 메서드를 지정한다.
    // @Payload 어노테이션은 웹 소켓 메시지의 페이로드 (전송하고자 하는 실제 데이터)를 메서드 매개변수에 바인딩한다.
    public ResponseEntity<ChattingMessage> sendWebSocket(@Payload ChattingMessage chattingMessage) {
        ChattingMessage message = chattingMessageService.send(chattingMessage);

        template.convertAndSend("sub/room/" + chattingMessage.getChattingRoom().getRoomNum(), message);
        // 웹 소켓을 통해 메시지를 해당 채팅방의 모든 클라이언트에게 브로드캐스트

        return ResponseEntity.ok(chattingMessage);
    }

    // 채팅방 목록 전체 조회 (관리자)
    @GetMapping("/admin")
    public ResponseEntity<List<ChattingRoom>> findAll() {
        return ResponseEntity.ok(chattingRoomService.findAll());
    }

    // 채팅방 목록 전체 조회 (사용자)
    @GetMapping("/member/{memberNum}")
    public ResponseEntity<List<ChattingRoom>> findAllByHostNumOrGuestNum(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(chattingRoomService.findAllByHostNumOrGuestNum(memberNum));
    }

    // 채팅방 목록에서 채팅방 입장
    @GetMapping("enter/{roomNum}")
    public ResponseEntity<List<ChattingMessage>> enterChattingRoom(@PathVariable Integer roomNum) {
        return ResponseEntity.ok(chattingMessageService.findAllByChattingRoomRoomNumOrderByTime(roomNum));
    }
}