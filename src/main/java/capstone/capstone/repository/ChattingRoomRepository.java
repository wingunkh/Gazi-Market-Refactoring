package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {
    ChattingRoom findByGuestMemberNumAndPostPostNum(Integer memberNum, Integer postNum);

    List<ChattingRoom> findAllByHostMemberNum(Integer memberNum);

    List<ChattingRoom> findAllByGuestMemberNum(Integer memberNum);
}