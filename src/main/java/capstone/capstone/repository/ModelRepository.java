package capstone.capstone.repository;

import capstone.capstone.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findAllByCategoryCategoryName(String categoryName);
}