package capstone.capstone.repository;

import capstone.capstone.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    @Query(value = "select p.picture_location from picture p where p.post_num = :post_num" , nativeQuery = true)
    List<String> getPictureLocation(@Param("post_num") Integer post_num);
}
