package capstone.capstone.repository;

import capstone.capstone.domain.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Integer> {
    List<ChattingMessage> findAllByChattingRoomRoomNumOrderByTime(Integer roomNum);
}