package capstone.capstone.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Category {
    @Id
    private String category_name;
}