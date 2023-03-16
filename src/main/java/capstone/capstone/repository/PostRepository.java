package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Spring Data JPA 사용
public interface PostRepository extends JpaRepository<Posts, Integer> {
    //날짜 순 내림차순 정렬
    @Query(value="select * from Posts order by Posts.updateat desc", nativeQuery = true)
    List<Posts> findDate();

    //카테고리 별 포스트 반환
    @Query(value="select * from Posts p where p.MODEL_NAME in (select m.MODEL_NAME from MODEL m where m.CATEGORY_NAME = :category) order by p.updateat desc", nativeQuery = true)
    List<Posts> findCategory(String category);

    //모델 별 포스트 반환
    @Query(value="select * from Posts p where p.MODEL_NAME = :model order by p.updateat desc", nativeQuery = true)
    List<Posts> findModel(String model);

    @Query(value="select p.user_no from Posts p where p.post_no == post_no", nativeQuery = true)
    int findHost(int post_no);

    @Query(value="select p.post_title from Posts p where p.post_no == post_no", nativeQuery = true)
    String findName(int post_no);

    @Query(value="select u.user_info from User u where u.user_no == (select p.user_no from Posts p where p.post_no == post_no)", nativeQuery = true)
    String findHostInfo(int post_no);
}