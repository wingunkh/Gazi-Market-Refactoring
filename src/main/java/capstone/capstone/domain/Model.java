package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "model")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Model {
    @Id
    private String model_name;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "market_price")
    private int market_price;
}
