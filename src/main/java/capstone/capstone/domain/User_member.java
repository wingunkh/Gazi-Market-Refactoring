package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_member")
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
@Getter
@Setter
public class User_member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "user_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="user_SEQUENCE_GENERATOR", sequenceName = "user_SQ", initialValue = 1, allocationSize = 1)
    protected Integer user_num;

    @Column(name = "profile_image")
    protected String profile_image;

    @Column(name = "user_acc")
    protected String user_acc;

    @Column(name = "nickname")
    protected String nickname;

    @Column(name = "Temperature")
    protected Integer Temperature;

    @Column(name = "Administrator")
    protected String Administrator;
}
