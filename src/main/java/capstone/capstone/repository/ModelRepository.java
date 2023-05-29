package capstone.capstone.repository;

import capstone.capstone.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    @Query(value = "select * from MODEL m WHERE m.CATEGORY_NAME = :category order by m.MODEL_NAME", nativeQuery = true)
    List<Model> findCategory(@Param("category")String category);

    @Query(value = "select m.CATEGORY_NAME from MODEL m WHERE m.MODEL_NAME = :model_name", nativeQuery = true)
    String findCategoryName(@Param("model_name")String model_name);
}
