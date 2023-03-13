package capstone.capstone.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "chatting")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class Chatting {
    @Id
    private int cht_no;
    @Column
    private int cht_room_num;

    @Column(name = "cht_member")
    private int cht_member;

    @Column(name = "cht_text")
    private String cht_text;

    @Column(name = "cht_time")
    private LocalDateTime cht_time;

    public int getCht_no() {
        return cht_no;
    }

    public void setCht_no(int cht_no) {
        this.cht_no = cht_no;
    }

    public int getCht_room_num() {
        return cht_room_num;
    }

    public void setCht_room_num(int cht_room_num) {
        this.cht_room_num = cht_room_num;
    }

    public int getCht_member() {
        return cht_member;
    }

    public void setCht_member(int cht_member) {
        this.cht_member = cht_member;
    }

    public String getCht_text() {
        return cht_text;
    }

    public void setCht_text(String cht_text) {
        this.cht_text = cht_text;
    }

    public LocalDateTime getCht_time() {
        return cht_time;
    }

    public void setCht_time(LocalDateTime cht_time) {
        this.cht_time = cht_time;
    }
}
