package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "USERS_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="USERS_SEQUENCE_GENERATOR", sequenceName = "USERS_SQ", initialValue = 1, allocationSize = 1)
    protected Integer userNum;

    protected String profileImage;

    protected String email;

    protected String nickname;

    protected String isAdministrator;

    protected Double latitude;

    protected Double longitude;
}