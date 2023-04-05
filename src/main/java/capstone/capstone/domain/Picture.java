package capstone.capstone.domain;

import capstone.capstone.idclass.Posts_Picture;
import lombok.Builder;
import lombok.NoArgsConstructor;
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
public class Picture implements Serializable {
    @Id
    private Integer post_no;

    @Id
    private String picture_location;

    public Picture(Integer post_no, String picture_location) {
        this.post_no = post_no;
        this.picture_location = picture_location;
    }

    public Integer getPost_no() {
        return post_no;
    }

    public void setPost_no(Integer post_no) {
        this.post_no = post_no;
    }

    public String getPicture_location() {
        return picture_location;
    }

    public void setPicture_location(String picture_location) {
        this.picture_location = picture_location;
    }
}
