package capstone.capstone.repository;

import capstone.capstone.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    @Query(value = "select p.picture_location from picture p where p.post_no = :post_no" , nativeQuery = true)
    List<String> getPictureLocationByPostNo(@Param("post_no") Integer post_no);
}
