package capstone.capstone.domain;
import java.io.Serializable;

//기본키가 복합키인 클래의 @IDClass 어노테이션 사용을 위한 클래스
public class chatting_room implements Serializable {
    private int cht_no;
    private int cht_room_num;
}