package capstone.capstone.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recognition")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class Recognition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "recognition_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="recognition_SEQUENCE_GENERATOR", sequenceName = "recognition_SQ", initialValue = 1, allocationSize = 1)
    private Integer recognition_num;

    @Column(name = "reporter_num")
    private Integer reporter_num;

    @Column(name = "post_num")
    private Integer post_num;

    @Column(name = "report_date")
    private LocalDateTime report_date;

    @Column(name = "status")
    private String status;

    public Integer getRecognition_num() {
        return recognition_num;
    }

    public void setRecognition_num(Integer recognition_num) {
        this.recognition_num = recognition_num;
    }

    public Integer getReporter_num() {
        return reporter_num;
    }

    public void setReporter_num(Integer reporter_num) {
        this.reporter_num = reporter_num;
    }

    public Integer getPost_num() {
        return post_num;
    }

    public void setPost_num(Integer post_num) {
        this.post_num = post_num;
    }

    public LocalDateTime getReport_date() {
        return report_date;
    }

    public void setReport_date(LocalDateTime report_date) {
        this.report_date = report_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}