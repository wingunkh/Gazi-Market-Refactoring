package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//간단한 CRUD 기능은 JpaRepository를 상속하는 것으로 구현이 가능하다.
public interface PostRepository extends JpaRepository<Posts, Integer> {
    //날짜 순 내림차순 정렬
    @Query(value="select * from Posts order by Posts.updateat desc", nativeQuery = true)
    List<Posts> findDate();

    //카테고리 별 포스트 반환
    @Query(value="select * from POST p where p.MODEL_NAME in (select m.MODEL_NAME from MODEL m where m.CATEGORY_NAME = :category) order by p.updateat desc", nativeQuery = true)
    List<Posts> findCategory(String category);
}
