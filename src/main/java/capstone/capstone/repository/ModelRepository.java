package capstone.capstone.repository;

import capstone.capstone.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    @Query(value = "SELECT * FROM Model m WHERE m.category_name = :category_name ORDER BY m.model_name", nativeQuery = true)
    List<Model> getModelName(@Param("category_name")String category_name);

    @Query(value = "SELECT m.category_name from Model m WHERE m.model_name = :model_name", nativeQuery = true)
    String getCategoryName(@Param("model_name")String model_name);
}