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
    @PostMapping("create/{guestNum}/{postNum}")
    public ResponseEntity<List<ChattingMessage>> createChattingRoom(
            @PathVariable Integer guestNum,
            // @PathVariable 어노테이션은 경로 변수를 추출할 때 사용한다.
            // @RequestParam 어노테이션은 쿼리 스트링의 쿼리 매개변수를 추출할 때 사용한다.
            @PathVariable Integer postNum
    ) {
        Integer roomNum = chattingRoomService.createChattingRoom(guestNum, postNum).getRoomNum();

        return ResponseEntity.ok(chattingMessageService.enterChattingRoom(roomNum));
    }

    // 채팅 전송
    @PostMapping("/{roomNum}/{memberNum}")
    public ResponseEntity<ChattingMessage> sendHTTP(
            @PathVariable Integer roomNum,
            @PathVariable Integer memberNum,
            @RequestBody ChattingMessage chattingMessage
    ) {
        return ResponseEntity.ok(chattingMessageService.send(roomNum, memberNum, chattingMessage));
    }

    // 채팅 전송
    @MessageMapping("/{roomNum}/{memberNum}")
    // @MessageMapping 어노테이션은 웹 소켓 엔드포인트에서 클라이언트로부터 메시지를 수신하는 핸들러 메서드를 지정한다.
    // @Payload 어노테이션은 웹 소켓 메시지의 페이로드 (전송하고자 하는 실제 데이터)를 메서드 매개변수에 바인딩한다.
    public ResponseEntity<ChattingMessage> sendWebSocket(
            @PathVariable Integer roomNum,
            @PathVariable Integer memberNum,
            @Payload ChattingMessage chattingMessage
    ) {
        ChattingMessage message = chattingMessageService.send(roomNum, memberNum, chattingMessage);

        // 웹 소켓을 통해 메시지를 해당 채팅방의 모든 클라이언트에게 브로드캐스트
        template.convertAndSend("sub/room/" + chattingMessage.getChattingRoom().getRoomNum(), message);

        return ResponseEntity.ok(chattingMessage);
    }

    // 채팅방 목록 전체 조회 (관리자)
    @GetMapping("/admin")
    public ResponseEntity<List<ChattingRoom>> findAllChattingRooms() {
        return ResponseEntity.ok(chattingRoomService.findAllChattingRooms());
    }

    // 채팅방 목록 전체 조회 (사용자)
    @GetMapping("/member/{memberNum}")
    public ResponseEntity<List<ChattingRoom>> findAllChattingRoomsByHostNumOrGuestNum(@PathVariable Integer memberNum) {
        return ResponseEntity.ok(chattingRoomService.findAllChattingRoomsByHostNumOrGuestNum(memberNum));
    }

    // 채팅방 목록에서 채팅방 입장
    @GetMapping("enter/{roomNum}")
    public ResponseEntity<List<ChattingMessage>> enterChattingRoom(@PathVariable Integer roomNum) {
        return ResponseEntity.ok(chattingMessageService.enterChattingRoom(roomNum));
    }
}