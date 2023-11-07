package capstone.capstone.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "POST_SEQUENCE_GENERATOR", sequenceName = "POST_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQUENCE_GENERATOR")
    protected Integer postNum;

    protected Integer userNum;

    protected String modelName;

    protected String grade;

    protected String status;

    protected Integer price;

    protected String postTitle;

    protected String postContent;

    protected LocalDateTime writtenDate;

    protected Integer isCaptured;

    public void setImageSource(int flag) {
        isCaptured = flag;
    }
}