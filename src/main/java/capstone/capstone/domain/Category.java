package capstone.capstone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// JPA를 통해 데이터베이스의 테이블과 매칭될 Class임을 명시
// JPA(Java Persistence API)란? 자바 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용하는 인터페이스 모음
// 구현체에는 Hibernate, OpenJPA 등이 있다.
@AllArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자 추가
@NoArgsConstructor
// 파라미터가 없는 기본 생성자 추가
@Getter
public class Category {
    @Id
    // 해당 속성을 기본키로 설정
    private String categoryName;
}