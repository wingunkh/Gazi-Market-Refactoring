package capstone.capstone.repository;

import capstone.capstone.domain.LikeHistory;
import capstone.capstone.idclass.History_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, History_Post> {
    List<LikeHistory> findAllByMemberNum(Integer memberNum);
}