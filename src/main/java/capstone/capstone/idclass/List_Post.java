package capstone.capstone.idclass;

import java.io.Serializable;

// 기본키가 복합키인 클래의 @IDClass 어노테이션 사용을 위한 클래스
public class List_Post implements Serializable {
    private int post_num;
    private int user_num;
}