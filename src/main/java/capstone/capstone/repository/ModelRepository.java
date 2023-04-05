package capstone.capstone.repository;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelRepository extends JpaRepository<Models, Integer> {
    @Query(value = "select * from MODELS m WHERE m.CATEGORY_NAME = :category order by m.MODEL_NAME", nativeQuery = true)
    List<Models> findCategory(@Param("category")String category);


    @Query(value = "select m.CATEGORY_NAME from MODELS m WHERE m.MODEL_NAME = :model_name", nativeQuery = true)
    String findCategoryName(@Param("model_name")String model_name);
}
