package capstone.capstone.repository;

import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

//Spring Data JPA 사용
public interface PostRepository extends JpaRepository<Posts, Integer> {
    // 승인 대기 상태가 아닌 즉, 모델명이 기타가 아닌 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name != '기타'", nativeQuery = true)
    List<Posts> findAllPosts();

    // 승인 대기 상태인 즉, 모델명이 기타인 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name = '기타'", nativeQuery = true)
    List<Posts> findAllWaitingApprovalPosts();

    // 승인 대기 게시글을 승인함 즉, 해당 승인 대기 게시글의 모델을 제일 최근에 추가된 모델로 변경
    @Modifying // 이 쿼리문이 데이터를 변경한다는 것을 알려주기 위해 추가
    @Query(value="UPDATE Post p SET p.model_name = :model_name WHERE p.post_no = :post_no", nativeQuery = true)
    void approvePost(@Param("post_no") int post_no, @Param("model_name") String model_name);

    // 승인 대기 게시글을 삭제
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post WHERE post_no = :post_no", nativeQuery = true)
    void rejectPost(@Param("post_no") int post_no);

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