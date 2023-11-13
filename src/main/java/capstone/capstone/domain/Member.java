package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "MEMBER_SEQUENCE_GENERATOR", sequenceName = "MEMBER_SQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE_GENERATOR")
    protected Integer memberNum;

    protected String profileImage;

    protected String email;

    protected String nickname;

    protected Boolean isAdministrator;

    protected Double latitude;

    protected Double longitude;
}