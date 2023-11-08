package capstone.capstone.repository;

import capstone.capstone.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// 데이터베이스에 접근하여 도메인 객체를 저장하고 관리하는 클래스임을 명시
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Spring Data JPA란? JPA 인터페이스의 구현체가 아닌 추상화 모듈이다.
    // save(), delete(), findById(), findAll() 등의 주요 메서드를 제공한다.
    // 또한 쿼리 메서드 기능을 제공한다.
    // Spring Data JPA는 메서드 이름 규칙을 준수한 메서드 호출 시 이를 분석하여 JPQL 쿼리를 실행한다.
    // JPQL이란? SQL을 추상화한 객체 지향 쿼리 언어이다. (결국 SQL로 변환된다.)
}