package capstone.capstone.dto;

import capstone.capstone.domain.ChattingMessage;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ChattingMessageResponse {
    Integer roomNum;

    Integer senderNum;

    String content;

    LocalDateTime time;

    String senderProfileImage;

    String senderNickname;

    public ChattingMessageResponse(ChattingMessage chatting, String senderProfileImage, String senderNickname) {
        this.roomNum = chatting.getRoomNum();
        this.senderNum = chatting.getSenderNum();
        this.content = chatting.getContent();
        this.time = chatting.getTime();
        this.senderProfileImage = senderProfileImage;
        this.senderNickname = senderNickname;
    }
}