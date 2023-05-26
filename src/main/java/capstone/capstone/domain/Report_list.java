package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report_list")
@DynamicInsert // INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate // UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
@Getter
@Setter
public class Report_list {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "report_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="report_SEQUENCE_GENERATOR", sequenceName = "report_SQ", initialValue = 1, allocationSize = 1)
    private Integer report_num;

    @Column(name = "reporter_num")
    private Integer reporter_num;

    @Column(name = "post_num")
    private Integer post_num;

    @Column(name = "report_date")
    private LocalDateTime report_date;

    public Report_list(Integer reporter_num, Integer post_num, LocalDateTime report_date) {
        this.reporter_num = reporter_num;
        this.post_num = post_num;
        this.report_date = report_date;
    }
}
