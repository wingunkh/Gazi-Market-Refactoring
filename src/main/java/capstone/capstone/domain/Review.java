package capstone.capstone.domain;

import capstone.capstone.idclass.Review_Post;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@Table(name = "review")
@IdClass(Review_Post.class)
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Review {
    @Id
    private int post_num;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "review_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="review_SEQUENCE_GENERATOR", sequenceName = "review_SQ", initialValue = 1, allocationSize = 1)
    private int review_num;

    @Column(name = "review_content")
    protected String review_content;
}