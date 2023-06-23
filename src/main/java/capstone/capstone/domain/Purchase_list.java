package capstone.capstone.domain;

import capstone.capstone.idclass.List_Post;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@Table(name = "purchase_list")
@IdClass(List_Post.class)
@DynamicInsert
@DynamicUpdate
public class Purchase_list {
    @Id
    private int post_num;

    @Id
    private int user_num;
}