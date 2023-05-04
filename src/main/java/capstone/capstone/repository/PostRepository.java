package capstone.capstone.repository;

import capstone.capstone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//Spring Data JPA 사용
public interface PostRepository extends JpaRepository<Post, Integer> {
    // 승인 대기 상태가 아니며 신고로 인해 숨김 처리 되지 않은 즉, 모델명이 기타가 아니면서 판매 상태가 숨김이 아닌 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name != '기타' AND p.status != '숨김' order by p.written_date desc", nativeQuery = true)
    List<Post> findAllPosts();

    //게시물 수정
    @Modifying
    @Transactional
    @Query(value="update Post p set p.model_name = :model_name, p.grade = :grade, p.status = :status, p.price = :price, " +
            "p.title = :title, p.content = :content " + "where p.post_num = :post_num")
    void modifyPost(@Param("post_num") Integer post_num,
                    @Param("model_name") String model_name,
                    @Param("grade") String grade,
                    @Param("status") String status,
                    @Param("price") Integer price,
                    @Param("title") String title,
                    @Param("content") String content);

    // 게시글 삭제
    @Modifying
    @Transactional
    @Query(value="delete from Post p where p.post_num = :post_num", nativeQuery = true)
    void deletePost(@Param("post_num") int post_num);

    // 승인 대기 상태인 즉, 모델명이 기타인 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name = '기타'", nativeQuery = true)
    List<Post> findAllWaitingApprovalPosts();

    // 승인 대기 게시글을 승인함 즉, 해당 승인 대기 게시글의 모델을 제일 최근에 추가된 모델로 변경
    @Modifying // 이 쿼리문이 데이터를 변경한다는 것을 알려주기 위해 추가
    @Query(value="UPDATE Post p SET p.model_name = :model_name WHERE p.post_num = :post_num", nativeQuery = true)
    void approvePost(@Param("post_num") int post_num, @Param("model_name") String model_name);

    // 승인 대기 게시글을 삭제
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post WHERE post_num = :post_num", nativeQuery = true)
    void rejectPost(@Param("post_num") int post_num);

    // 날짜 내림차순 기준 포스트 목록 반환
    @Query(value="select * from Post order by Post.written_date desc", nativeQuery = true)
    List<Post> findDate();

    // 카테고리 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME in (select m.MODEL_NAME from MODEL m where m.CATEGORY_NAME = :category) order by p.written_date desc", nativeQuery = true)
    List<Post> findCategory(@Param("category")String category);

    // 모델 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME = :model order by p.written_date desc", nativeQuery = true)
    List<Post> findModel(@Param("model")String model);

    // 게시글 번호로 해당 게시글 작성자 번호 반환
    @Query(value="select p.user_num from Post p where p.post_num = :post_num", nativeQuery = true)
    int findHost(@Param("post_num")int post_num);

    // 게시글 번호로 해당 게시글 제목 반환
    @Query(value="select p.post_title from Post p where p.post_num = :post_num", nativeQuery = true)
    String findName(@Param("post_num")int post_num);

    // 게시글 번호로 해당 게시글 작성자의 작성자 정보 반환
    @Query(value="select u.nickname from User_member u where u.user_num = (select p.user_num from Post p where p.post_num = :post_num)", nativeQuery = true)
    String findHostInfo(@Param("post_num") int post_num);

    // 해당 문자열이 들어가는 게시글 목록 반환
    @Query(value="select * from Post p where p.post_title like '%:name%' order by p.written_date :type", nativeQuery = true)
    List<Post> findIncludeName(@Param("type") String type, @Param("name") String name);
}