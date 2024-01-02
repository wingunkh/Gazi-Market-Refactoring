package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "CHATTING_ROOM_SEQUENCE_GENERATOR", sequenceName = "CHATTING_ROOM_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATTING_ROOM_SEQUENCE_GENERATOR")
    private Integer roomNum;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member host;

    @ManyToOne
    private Member guest;
}