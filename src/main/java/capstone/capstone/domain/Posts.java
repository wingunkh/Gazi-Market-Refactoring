package capstone.capstone.domain;

import lombok.Builder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
//@Builder
public class Posts {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "post_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="post_SEQUENCE_GENERATOR", sequenceName = "post_SQ", initialValue = 1, allocationSize = 1)
    protected Integer post_no;

    @Column(name = "model_name")
    protected String model_name;

    @Column(name = "user_no")
    protected Integer user_no;

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

    @Column(name = "updateat")
    protected LocalDateTime updateat;

    public LocalDateTime getUpdateat() {
        return updateat;
    }

    public void setUpdateat(LocalDateTime updateat) {
        this.updateat = updateat;
    }

    public Integer getPost_no() {
        return post_no;
    }

    public void setPost_no(Integer post_no) {
        this.post_no = post_no;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
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
}