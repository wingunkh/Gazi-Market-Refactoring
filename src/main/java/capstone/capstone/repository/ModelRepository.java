package capstone.capstone.repository;

import capstone.capstone.domain.Models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Models, Integer> {
}
