package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@Table(name = "user_member")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class User_Member {
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

    @Column(name = "temperature")
    protected Integer temperature;

    @Column(name = "administrator")
    protected String administrator;

    @Column(name = "latitude")
    protected Double latitude;

    @Column(name = "longitude")
    protected Double longitude;
}