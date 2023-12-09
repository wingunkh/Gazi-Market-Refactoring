package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "CHATTING_MESSAGE_SEQUENCE_GENERATOR", sequenceName = "CHATTING_MESSAGE_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChattingMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATTING_MESSAGE_SEQUENCE_GENERATOR")
    private Integer chattingNum;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @ManyToOne
    private Member member;

    private String content;

    private LocalDateTime time;
}