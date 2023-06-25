package capstone.capstone.repository;

import capstone.capstone.domain.Like_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface LikeListRepository extends JpaRepository<Like_list, Integer> {
    @Query(value = "SELECT l.post_num FROM Like_list l WHERE l.user_num = :user_num", nativeQuery = true)
    List<Integer> getLikeList(@Param("user_num") int user_num);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Like_list l  WHERE l.post_num = :post_num", nativeQuery = true)
    void deletePost(@Param("post_num") int post_num);
}