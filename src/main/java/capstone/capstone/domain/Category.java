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
@DynamicInsert  //INSERT 시 NULL인 부분을 제외하기 위해 사용, 동적 인서트
@DynamicUpdate  //UPDATE 시 NULL인 부분을 제외하기 위해 사용, 동적 업데이트
@Getter
@Setter
public class Category {
    @Id
    private String category_name;

}
