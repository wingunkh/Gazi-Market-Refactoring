package capstone.capstone.domain;

import capstone.capstone.idclass.Posts_Picture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture")
@DynamicInsert
@DynamicUpdate
@IdClass(Posts_Picture.class) //복합키 매핑을 위한 어노테이션
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Picture implements Serializable {
    @Id
    private Integer post_num;

    @Id
    private String picture_location;

    public Picture(Integer post_num, String picture_location) {
        this.post_num = post_num;
        this.picture_location = picture_location;
    }

}
