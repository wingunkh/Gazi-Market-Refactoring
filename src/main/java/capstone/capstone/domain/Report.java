package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "REPORT_SEQUENCE_GENERATOR", sequenceName = "REPORT_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_SEQUENCE_GENERATOR")
    protected Integer reportNum;

    @ManyToOne
    protected Member member;

    protected Integer postNum;

    protected LocalDateTime reportedDate;
}