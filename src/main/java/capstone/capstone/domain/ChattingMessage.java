package capstone.capstone.domain;

import capstone.capstone.idclass.ChattingMessage_ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "CHATTING_MESSAGE_SEQUENCE_GENERATOR", sequenceName = "CHATTING_MESSAGE_SQ", initialValue = 1, allocationSize = 1)
// 시퀀스 생성
// 시퀀스란? 유일한 값을 순서대로 생성하는 객체
@IdClass(ChattingMessage_ChattingRoom.class)
// 해당 클래스 정보를 통해 복합키 매핑
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChattingMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATTING_MESSAGE_SEQUENCE_GENERATOR")
    // 기본키 생성 전략 명시
    private Integer chattingNum;

    @Id
    private Integer roomNum;

    private Integer senderNum;

    private String content;

    private LocalDateTime time;
}