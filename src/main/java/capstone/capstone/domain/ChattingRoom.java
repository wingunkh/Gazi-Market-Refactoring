package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "CHATTING_ROOM_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="CHATTING_ROOM_SEQUENCE_GENERATOR", sequenceName = "CHATTING_ROOM_SQ", initialValue = 1, allocationSize = 1)
    private Integer roomNum;

    private Integer postNum;

    private Integer hostNum;

    private Integer guestNum;

    public ChattingRoom(Integer postNum, Integer hostNum, Integer guestNum) {
        this.postNum = postNum;
        this.hostNum = hostNum;
        this.guestNum = guestNum;
    }
}