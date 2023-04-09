package capstone.capstone.repository;

import capstone.capstone.domain.PostApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostApprovalRepository extends JpaRepository<PostApproval, Integer> {
    // 날짜 내림차순 기준 승인 대기 목록 반환
    @Query(value="select * from Post_approval order by Post_approval.updateat desc", nativeQuery = true)
    List<PostApproval> findDate();
}
