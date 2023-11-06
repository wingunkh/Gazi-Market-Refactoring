package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Model {
    @Id
    private String modelName;

    private String categoryName;

    private Integer marketPrice;
}