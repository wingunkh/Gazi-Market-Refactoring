package capstone.capstone.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class History_Post implements Serializable {
    private Integer postNum;

    private Integer memberNum;
}