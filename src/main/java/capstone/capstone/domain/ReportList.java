package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "REPORT_LIST_SEQUENCE_GENERATOR", sequenceName = "REPORT_LIST_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_LIST_SEQUENCE_GENERATOR")
    protected Integer reportNum;

    protected Integer reporterNum;

    protected Integer postNum;

    protected LocalDateTime reportedDate;

    public ReportList(Integer reporterNum, Integer postNum, LocalDateTime reportedDate) {
        this.reporterNum = reporterNum;
        this.postNum = postNum;
        this.reportedDate = reportedDate;
    }
}