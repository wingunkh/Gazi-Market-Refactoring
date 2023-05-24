package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@DynamicInsert // INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate // UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "post_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="post_SEQUENCE_GENERATOR", sequenceName = "post_SQ", initialValue = 1, allocationSize = 1)
    protected Integer post_num;

    @Column(name = "user_num")
    protected Integer user_num;

    @Column(name = "model_name")
    protected String model_name;

    @Column(name = "grade")
    protected String grade;

    @Column(name = "status")
    protected String status;

    @Column(name = "price")
    protected Integer price;

    @Column(name = "post_title")
    protected String post_title;

    @Column(name = "post_content")
    protected String post_content;

    @Column(name = "written_date")
    protected LocalDateTime written_date;

    @Column(name = "iscaptured")
    protected Integer isCaptured;
}