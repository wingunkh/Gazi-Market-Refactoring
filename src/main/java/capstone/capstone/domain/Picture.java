package capstone.capstone.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Picture {
    @Id
    private String location;

    @ManyToOne
    private Post post;
}