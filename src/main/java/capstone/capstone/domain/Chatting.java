package capstone.capstone.domain;

import capstone.capstone.idclass.Chatting_ChattingRoom;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chatting")
@IdClass(Chatting_ChattingRoom.class)
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Chatting {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "chatting_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="chatting_SEQUENCE_GENERATOR", sequenceName = "cht_SQ", initialValue = 1, allocationSize = 1)
    private int cht_num;

    @Id
    private int cht_room_num;

    @Column(name = "cht_member")
    private int cht_member;

    @Column(name = "cht_text")
    private String cht_text;

    @Column(name = "cht_time")
    private LocalDateTime cht_time;
}