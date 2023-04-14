package capstone.capstone.domain;

import capstone.capstone.idclass.List_Post;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "like_list")
@IdClass(List_Post.class) //복합키 매핑을 위한 어노테이션
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
public class Like_list {
    @Id
    private int post_num;

    @Id
    private int user_num;

}
