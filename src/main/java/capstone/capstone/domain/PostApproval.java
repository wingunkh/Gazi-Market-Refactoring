package capstone.capstone.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_approval")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class PostApproval {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "PA_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="PA_SEQUENCE_GENERATOR", sequenceName = "PA_SQ", initialValue = 1, allocationSize = 1)
    private Integer waiting_num;

    @Column(name = "post_num")
    private Integer post_num;

    @Column(name = "user_no")
    private Integer user_no;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "grade")
    private String grade;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private Integer price;

    @Column(name = "post_title")
    private String post_title;

    @Column(name = "post_content")
    private String post_content;

    @Column(name = "updateat")
    private LocalDateTime updateat;

    public Integer getRecognition_num() {
        return waiting_num;
    }

    public void setRecognition_num(Integer recognition_num) {
        this.waiting_num = waiting_num;
    }

    public Integer getPost_num() {
        return post_num;
    }

    public void setPost_num(Integer post_num) {
        this.post_num = post_num;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public LocalDateTime getUpdateat() {
        return updateat;
    }

    public void setUpdateat(LocalDateTime updateat) {
        this.updateat = updateat;
    }
}