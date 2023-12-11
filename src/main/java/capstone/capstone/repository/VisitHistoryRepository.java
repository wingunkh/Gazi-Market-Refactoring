package capstone.capstone.repository;

import capstone.capstone.domain.VisitHistory;
import capstone.capstone.idclass.History_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitHistoryRepository extends JpaRepository<VisitHistory, History_Post> {
    List<VisitHistory> findAllByMemberNum(Integer memberNum);
}