package capstone.capstone.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "chattingroom")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "chattingroom_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="chattingroom_SEQUENCE_GENERATOR", sequenceName = "chtroom_SQ", initialValue = 1, allocationSize = 1)
    private int cht_room_num;

    @Column(name = "post_no")
    private int post_no;

    @Column(name = "host_member")
    private int host_member;

    @Column(name = "guest_member")
    private int guest_member;

    public int getCht_room_num() {
        return cht_room_num;
    }

    public void setCht_room_num(int cht_room_num) {
        this.cht_room_num = cht_room_num;
    }

    public int getPost_no() {
        return post_no;
    }

    public void setPost_no(int post_no) {
        this.post_no = post_no;
    }

    public int getHost_member() {
        return host_member;
    }

    public void setHost_member(int host_member) {
        this.host_member = host_member;
    }

    public int getGuest_member() {
        return guest_member;
    }

    public void setGuest_member(int guest_member) {
        this.guest_member = guest_member;
    }
}
