package capstone.capstone.repository;

import capstone.capstone.domain.Location;
import capstone.capstone.domain.Post;
import capstone.capstone.domain.User_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//Spring Data JPA 사용
public interface PostRepository extends JpaRepository<Post, Integer> {
    // READ : 승인 대기 상태가 아니며 신고로 인해 숨김 처리 되지 않은 즉, 모델명이 기타가 아니면서 판매 상태가 숨김이 아닌 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name != '기타' and p.status != '숨김' order by p.written_date desc", nativeQuery = true)
    List<Post> findAllPosts();

    // UPDATE : 게시물 수정
    @Modifying // 이 쿼리문이 데이터를 변경한다는 것을 알려주기 위해 추가
    @Transactional
    @Query(value="update Post p set p.model_name = :model_name, p.grade = :grade, p.status = :status, p.price = :price, " +
            "p.post_title = :post_title, p.post_content = :post_content " + "where p.post_num = :post_num", nativeQuery = true)
    void updatePost(@Param("post_num") int post_num,
                    @Param("model_name") String model_name,
                    @Param("grade") String grade,
                    @Param("status") String status,
                    @Param("price") Integer price,
                    @Param("post_title") String post_title,
                    @Param("post_content") String post_content);

    // DELETE : 게시글 삭제
    @Modifying
    @Transactional
    @Query(value="delete from Post p where p.post_num = :post_num", nativeQuery = true)
    void deletePost(@Param("post_num") int post_num);

    // 승인 대기 상태인 즉, 모델명이 기타인 포스트 목록 반환
    @Query(value="select * from Post p where p.model_name = '기타'", nativeQuery = true)
    List<Post> findAllWaitingApprovalPosts();

    // 승인 대기 게시글을 승인함 즉, 해당 승인 대기 게시글의 모델명을 기타에서 특정 모델명으로 변경
    @Modifying
    @Query(value="UPDATE Post p SET p.model_name = :model_name WHERE p.post_num = :post_num", nativeQuery = true)
    void approvePost(@Param("post_num") int post_num, @Param("model_name") String model_name);

    // 승인 대기 게시글을 삭제
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post WHERE post_num = :post_num", nativeQuery = true)
    void rejectPost(@Param("post_num") int post_num);

    // 카테고리 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME in (select m.MODEL_NAME from MODEL m where m.CATEGORY_NAME = :category) and p.status != '숨김' order by p.written_date desc", nativeQuery = true)
    List<Post> findCategory(@Param("category")String category);

    // 모델 별 게시글 목록 반환
    @Query(value="select * from Post p where p.MODEL_NAME = :model and p.status != '숨김' order by p.written_date desc", nativeQuery = true)
    List<Post> findModel(@Param("model")String model);

    // 게시글 번호로 해당 게시글 작성자 번호 반환
    @Query(value="select p.user_num from Post p where p.post_num = :post_num and p.status != '숨김'", nativeQuery = true)
    int findHost(@Param("post_num")int post_num);

    // 게시글 번호로 해당 게시글 제목 반환
    @Query(value="select p.post_title from Post p where p.post_num = :post_num and p.status != '숨김'", nativeQuery = true)
    String findName(@Param("post_num")int post_num);

    // 게시글 번호로 해당 게시글 작성자의 작성자 정보 반환
    @Query(value="select u.nickname from User_member u where u.user_num = (select p.user_num from Post p where p.post_num = :post_num and p.status != '숨김')", nativeQuery = true)
    String findHostInfo(@Param("post_num") int post_num);

    // 해당 문자열이 들어가는 게시글 목록 반환
    @Query(value="select * from Post p where p.grade like '%:name%' OR p.post_content like '%:name%' OR p.post_title like '%:name%' and p.status != '숨김' order by p.written_date desc", nativeQuery = true)
    List<Post> findIncludeNamed(@Param("name") String name);

    @Query(value="select * from Post p where p.grade like '%:name%' OR p.post_content like '%:name%' OR p.post_title like '%:name%' and p.status != '숨김' order by p.written_date", nativeQuery = true)
    List<Post> findIncludeNamea(@Param("name") String name);

    @Query(value = "select avg(p.price) FROM Post p GROUP BY p.model_name, p.grade HAVING p.model_name = :model AND p.grade = :grade", nativeQuery = true)
    double findFairPrice(@Param("model") String model, @Param("grade") String grade);

    @Query(value = "select u.longitude FROM User_member u where u.user_num = (select distinct p.user_num FROM Post p where p.post_num = :post_num)", nativeQuery = true)
    double findPostLocation_lo(@Param("post_num")int post_num);

    @Query(value = "select u.latitude FROM User_member u where u.user_num = (select distinct p.user_num FROM Post p where p.post_num = :post_num)", nativeQuery = true)
    double findPostLocation_la(@Param("post_num")int post_num);

    @Query(value = "SELECT u.user_num FROM User_member u WHERE ACOS(SIN(RADIANS(:latitude)) * SIN(RADIANS(u.latitude)) + COS(RADIANS(:latitude)) * COS(RADIANS(u.latitude)) * COS(RADIANS(:longitude - u.longitude))) * 6371 <= :distance", nativeQuery = true)
    List<Integer> findAroundLocation(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("distance")double distance);

    @Query(value = "select u.latitude FROM USER_MEMBER u where u.user_num = :user_num", nativeQuery = true)
    double findLa(@Param("user_num")int user_num);

    @Query(value = "select u.longitude FROM USER_MEMBER u where u.user_num = :user_num", nativeQuery = true)
    double findLo(@Param("user_num")int user_num);

    @Query(value = "SELECT * from Post p where p.user_num = :user_num", nativeQuery = true)
    List<Post> findAllUser(@Param("user_num")int user_num);
}
