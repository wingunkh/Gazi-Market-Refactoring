package capstone.capstone.repository;

import capstone.capstone.domain.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Integer> {
    List<ChattingMessage> findAllByChattingRoomRoomNumOrderByTime(Integer roomNum);
}