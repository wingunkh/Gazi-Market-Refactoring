package capstone.capstone.repository;

import capstone.capstone.domain.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ChattingRepository extends JpaRepository<ChattingMessage, Integer> {
    @Query(value = "SELECT * FROM Chatting ch WHERE ch.cht_room_num = :cht_room_num ORDER BY ch.cht_time", nativeQuery = true)
    List<ChattingMessage> enterChattingRoom(@Param("cht_room_num")int cht_room_num);

    @Query(value = "SELECT * FROM (SELECT ch.cht_text FROM Chatting ch WHERE ch.cht_room_num = :cht_room_num ORDER BY ch.cht_time desc) WHERE rownum <= 1", nativeQuery = true)
    String getLastMsg(@Param("cht_room_num") int cht_room_num);

    @Query(value = "SELECT * FROM (SELECT ch.cht_time FROM Chatting ch WHERE ch.cht_room_num = :cht_room_num ORDER BY ch.cht_time desc) WHERE rownum <= 1", nativeQuery = true)
    LocalDateTime getLastTime(@Param("cht_room_num") int cht_room_num);
}