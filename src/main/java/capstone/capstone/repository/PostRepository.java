package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//간단한 CRUD 기능은 JpaRepository를 상속하는 것으로 구현이 가능하다.
public interface PostRepository extends JpaRepository<Posts, Integer> {
    @Query(value="select * from POST", nativeQuery = true)
    List<Posts> findALLList();
}
