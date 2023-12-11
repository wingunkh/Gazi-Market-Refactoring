package capstone.capstone.repository;

import capstone.capstone.domain.LikeHistory;
import capstone.capstone.idclass.History_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikeHistoryRepository extends JpaRepository<LikeHistory, History_Post> {
    List<LikeHistory> findAllByMemberNum(Integer memberNum);
}