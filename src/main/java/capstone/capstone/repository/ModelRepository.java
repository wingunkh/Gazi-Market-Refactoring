package capstone.capstone.repository;

import capstone.capstone.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findAllByCategoryCategoryName(String categoryName);
}