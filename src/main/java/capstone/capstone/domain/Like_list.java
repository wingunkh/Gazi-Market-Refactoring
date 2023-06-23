package capstone.capstone.domain;

import capstone.capstone.idclass.List_Post;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "like_list")
@IdClass(List_Post.class)
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Like_list {
    @Id
    private int post_num;

    @Id
    private int user_num;
}