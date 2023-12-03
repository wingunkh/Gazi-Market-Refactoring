package capstone.capstone.domain;

import capstone.capstone.idclass.History_Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(History_Post.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisitHistory {
    @Id
    private Integer postNum;

    @Id
    private Integer memberNum;
}