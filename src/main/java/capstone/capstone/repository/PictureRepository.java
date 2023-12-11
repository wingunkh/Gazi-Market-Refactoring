package capstone.capstone.repository;

import capstone.capstone.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {
    Picture findByPostPostNum(Integer postNum);
}