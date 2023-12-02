package capstone.capstone.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class List_Post implements Serializable {
    private Integer postNum;

    private Integer userNum;
}