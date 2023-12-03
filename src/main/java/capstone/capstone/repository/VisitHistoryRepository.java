package capstone.capstone.repository;

import capstone.capstone.domain.VisitHistory;
import capstone.capstone.idclass.History_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisitHistoryRepository extends JpaRepository<VisitHistory, History_Post> {
    List<VisitHistory> findAllByMemberNum(Integer memberNum);
}