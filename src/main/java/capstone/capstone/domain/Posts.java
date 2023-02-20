package capstone.capstone.domain;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@DynamicInsert  //insert시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //update시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_num;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "user_no")
    private Integer user_no;

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


}
