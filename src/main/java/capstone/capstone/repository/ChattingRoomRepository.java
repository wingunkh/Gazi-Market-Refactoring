package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {
    ChattingRoom findByGuestNumAndPostNum(Integer guestNum, Integer postNum);

    List<ChattingRoom> findAllByHostNumOrGuestNum(Integer hostNum, Integer guestNum);
}