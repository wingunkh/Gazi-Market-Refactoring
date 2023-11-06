package capstone.capstone.domain;

import capstone.capstone.idclass.List_Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@IdClass(List_Post.class)
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseList {
    @Id
    private Integer postNum;

    @Id
    private Integer userNum;
}