package capstone.capstone.extendedDomain;

import capstone.capstone.domain.ChattingMessage;
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

    String cht_member_profile;

    public ChattingWithName(ChattingMessage chatting, String cht_member_name) {
        this.cht_room_num = chatting.getRoomNum();
        this.cht_member = chatting.getSenderNum();
        this.cht_text = chatting.getContent();
        this.cht_time = chatting.getTime();
        this.cht_member_name = cht_member_name;
    }
}