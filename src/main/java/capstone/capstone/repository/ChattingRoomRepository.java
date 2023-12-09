package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {
    ChattingRoom findByPostNumAndGuestNum(Integer postNum, Integer guestNum);

    List<ChattingRoom> findAllByHostNumOrGuestNum(Integer hostNum, Integer guestNum);
}