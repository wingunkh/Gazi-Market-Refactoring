package capstone.capstone.extendedDomain;

import capstone.capstone.domain.Chatting;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public
class ChattingWithName {
    private int cht_room_num;

    private int cht_member;

    private String cht_text;

    private LocalDateTime cht_time;
    String cht_member_name;

    public ChattingWithName(Chatting chatting, String cht_member_name) {
        this.cht_room_num = chatting.getCht_room_num();
        this.cht_member = chatting.getCht_member();
        this.cht_text = chatting.getCht_text();
        this.cht_time = chatting.getCht_time();
        this.cht_member_name = cht_member_name;
    }
}
