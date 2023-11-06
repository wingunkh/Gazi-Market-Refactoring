package capstone.capstone.domain;

import capstone.capstone.idclass.ChattingMessage_ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(ChattingMessage_ChattingRoom.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChattingMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "CHATTING_MESSAGE_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="CHATTING_MESSAGE_SEQUENCE_GENERATOR", sequenceName = "CHATTING_MESSAGE_SQ", initialValue = 1, allocationSize = 1)
    private Integer chattingNum;

    @Id
    private Integer roomNum;

    private Integer senderNum;

    private String content;

    private LocalDateTime time;
}