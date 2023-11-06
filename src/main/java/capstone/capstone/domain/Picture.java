package capstone.capstone.domain;

import capstone.capstone.idclass.Picture_Post;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Picture_Post.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Picture implements Serializable {
    @Id
    private Integer postNum;

    @Id
    private String location;
}