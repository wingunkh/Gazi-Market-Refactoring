package capstone.capstone.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
// 기본키가 복합키인 클래의 @IDClass 어노테이션 사용을 위한 클래스
public class History_Post implements Serializable {
    private Integer memberNum;

    private Integer postNum;
}