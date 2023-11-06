package capstone.capstone.domain;

import capstone.capstone.idclass.List_Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(List_Post.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisitList {
    @Id
    private Integer postNum;

    @Id
    private Integer userNum;
}