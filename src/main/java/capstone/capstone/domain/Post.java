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
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQUENCE_GENERATOR")
    protected Integer postNum;

    @ManyToOne
    protected Model model;

    protected String postTitle;

    protected String postContent;

    protected Integer isCaptured;

    protected String grade;

    protected Integer price;

    protected LocalDateTime writtenDate;

    protected String status;

    @ManyToOne
    protected Member member;

    public void setImageSource(int flag) {
        isCaptured = flag;
    }
}