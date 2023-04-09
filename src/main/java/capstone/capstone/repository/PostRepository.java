package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Spring Data JPA 사용
public interface PostRepository extends JpaRepository<Posts, Integer> {
    // 날짜 내림차순 기준 포스트 목록 반환
    @Query(value="select * from Post order by Posts.updateat desc", nativeQuery = true)
    List<Posts> findDate();

    // 카테고리 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME in (select m.MODEL_NAME from MODEL m where m.CATEGORY_NAME = :category) order by p.updateat desc", nativeQuery = true)
    List<Posts> findCategory(@Param("category")String category);

    // 모델 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME = :model order by p.updateat desc", nativeQuery = true)
    List<Posts> findModel(@Param("model")String model);

    // 게시글 번호로 해당 게시글 작성자 번호 반환
    @Query(value="select p.user_no from Post p where p.post_no = :post_no", nativeQuery = true)
    int findHost(@Param("post_no")int post_no);

    // 게시글 번호로 해당 게시글 제목 반환
    @Query(value="select p.post_title from Post p where p.post_no = :post_no", nativeQuery = true)
    String findName(@Param("post_no")int post_no);

    // 게시글 번호로 해당 게시글 작성자의 작성자 정보 반환
    @Query(value="select u.user_info from Users u where u.user_no = (select p.user_no from Post p where p.post_no = :post_no)", nativeQuery = true)
    String findHostInfo(@Param("post_no") int post_no);

    // 해당 문자열이 들어가는 게시글 목록 반환
    @Query(value="select * from Post p where p.post_title like '%:name%' order by p.updateat :type", nativeQuery = true)
    List<Posts> findIncludeName(@Param("type") String type, @Param("name") String name);
}