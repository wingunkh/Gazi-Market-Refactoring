package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@Table(name = "chattingroom")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "chattingroom_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="chattingroom_SEQUENCE_GENERATOR", sequenceName = "chtroom_SQ", initialValue = 1, allocationSize = 1)
    private int cht_room_num;

    @Column(name = "post_num")
    private int post_num;

    @Column(name = "host_member")
    private int host_member;

    @Column(name = "guest_member")
    private int guest_member;
}