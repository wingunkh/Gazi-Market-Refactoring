package capstone.capstone.repository;

import capstone.capstone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // Read
    @Query(value="SELECT * FROM Post p WHERE p.model_name != '기타' AND p.status = '판매중' ORDER BY p.written_date desc", nativeQuery = true)
    List<Post> getAllPost();

    @Query(value="SELECT * FROM Post p WHERE p.status = '숨김' ORDER BY p.written_date desc", nativeQuery = true)
    List<Post> getHiddenPost();

    @Query(value="SELECT * FROM Post p WHERE p.model_name = '기타'", nativeQuery = true)
    List<Post> getAllWaitingApprovalPost();

    @Query(value="SELECT * FROM Post p WHERE p.model_name IN (SELECT m.model_name FROM Model m WHERE m.category_name = :category) AND p.status = '판매중' ORDER BY p.written_date desc", nativeQuery = true)
    List<Post> getPostByCategory(@Param("category")String category);

    @Query(value="SELECT * FROM Post p WHERE p.model_name = :model AND p.status = '판매중' ORDER BY p.written_date desc", nativeQuery = true)
    List<Post> getPostByModel(@Param("model")String model);

    @Query(value="SELECT p.user_num FROM Post p WHERE p.post_num = :post_num AND p.status = '판매중'", nativeQuery = true)
    int findHost(@Param("post_num")int post_num);

    @Query(value="SELECT p.post_title FROM Post p WHERE p.post_num = :post_num AND p.status = '판매중'", nativeQuery = true)
    String getPostName(@Param("post_num")int post_num);

    @Query(value="SELECT u.nickname FROM User_Member u WHERE u.user_num = (SELECT p.user_num FROM Post p WHERE p.post_num = :post_num AND p.status = '판매중')", nativeQuery = true)
    String getHostInfo(@Param("post_num") int post_num);

    @Query(value="SELECT * FROM Post p WHERE p.grade LIKE %:name% OR p.post_content LIKE %:name% OR p.post_title like %:name% AND p.status = '판매중' ORDER BY p.written_date desc", nativeQuery = true)
    List<Post> getPostByName(@Param("name") String name);

    @Query(value="SELECT * FROM Post p WHERE p.grade like %:name% OR p.post_content like %:name% OR p.post_title like %:name% AND p.status = '판매중' ORDER BY p.written_date", nativeQuery = true)
    List<Post> findIncludeNamea(@Param("name") String name);

    @Query(value = "SELECT avg(p.price) FROM Post p GROUP BY p.model_name, p.grade HAVING p.model_name = :model AND p.grade = :grade", nativeQuery = true)
    Double getMarketPrice(@Param("model") String model, @Param("grade") String grade);

    @Query(value = "SELECT u.longitude FROM User_Member u WHERE u.user_num = (SELECT distinct p.user_num FROM Post p WHERE p.post_num = :post_num)", nativeQuery = true)
    double getPostLocationLo(@Param("post_num")int post_num);

    @Query(value = "SELECT u.latitude FROM User_Member u WHERE u.user_num = (SELECT distinct p.user_num FROM Post p WHERE p.post_num = :post_num)", nativeQuery = true)
    double getPostLocationLa(@Param("post_num")int post_num);

    @Query(value = "SELECT u.user_num FROM User_Member u WHERE ACOS(SIN(RADIANS(:latitude)) * SIN(RADIANS(u.latitude)) + COS(RADIANS(:latitude)) * COS(RADIANS(u.latitude)) * COS(RADIANS(:longitude - u.longitude))) * 6371 <= :distance", nativeQuery = true)
    List<Integer> getAroundPost(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("distance")double distance);

    @Query(value = "SELECT u.latitude FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    double getLa(@Param("user_num")int user_num);

    @Query(value = "SELECT u.longitude FROM User_Member u WHERE u.user_num = :user_num", nativeQuery = true)
    double getLo(@Param("user_num")int user_num);

    @Query(value = "SELECT * FROM Post p WHERE p.user_num = :user_num", nativeQuery = true)
    List<Post> findAllUser(@Param("user_num")int user_num);

    @Query(value = "SELECT * FROM Post WHERE TRUNC(written_date) = TRUNC(SYSDATE)", nativeQuery = true)
    List<Post> getTodayPost();

    @Query(value = "SELECT * FROM Post WHERE user_num = :user_num ORDER BY written_date", nativeQuery = true)
    List<Post> getSoldOutPost(@Param("user_num") int user_num);

    // Update
    @Modifying
    @Transactional
    @Query(value="UPDATE Post p SET p.model_name = :model_name, p.grade = :grade, p.status = :status, p.price = :price, " +
            "p.post_title = :post_title, p.post_content = :post_content " + "WHERE p.post_num = :post_num", nativeQuery = true)
    void updatePost(@Param("post_num") int post_num,
                    @Param("model_name") String model_name,
                    @Param("grade") String grade,
                    @Param("status") String status,
                    @Param("price") Integer price,
                    @Param("post_title") String post_title,
                    @Param("post_content") String post_content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '숨김' WHERE post_num = :post_num", nativeQuery = true)
    void hidePost(@Param("post_num") Integer post_num);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '판매중' WHERE post_num = :post_num", nativeQuery = true)
    void exposureHiddenPost(@Param("post_num") Integer post_num);

    @Modifying
    @Query(value="UPDATE Post p SET p.model_name = :model_name WHERE p.post_num = :post_num", nativeQuery = true)
    void approvePost(@Param("post_num") int post_num, @Param("model_name") String model_name);

    @Modifying
    @Transactional
    @Query(value="UPDATE Post p SET p.status = '판매완료' WHERE p.post_num = :post_num", nativeQuery = true)
    void soldOut(@Param("post_num") int post_num);

    // Delete
    @Modifying
    @Transactional
    @Query(value="DELETE FROM Post p WHERE p.post_num = :post_num", nativeQuery = true)
    void deletePost(@Param("post_num") int post_num);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post WHERE post_num = :post_num", nativeQuery = true)
    void rejectPost(@Param("post_num") int post_num);
}
