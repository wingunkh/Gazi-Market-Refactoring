package capstone.capstone.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report_list")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@Setter
public class Report_list {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "report_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="report_SEQUENCE_GENERATOR", sequenceName = "report_SQ", initialValue = 1, allocationSize = 1)
    protected Integer report_num;

    @Column(name = "reporter_num")
    protected Integer reporter_num;

    @Column(name = "post_num")
    protected Integer post_num;

    @Column(name = "report_date")
    protected LocalDateTime report_date;

    public Report_list(Integer reporter_num, Integer post_num, LocalDateTime report_date) {
        this.reporter_num = reporter_num;
        this.post_num = post_num;
        this.report_date = report_date;
    }
}