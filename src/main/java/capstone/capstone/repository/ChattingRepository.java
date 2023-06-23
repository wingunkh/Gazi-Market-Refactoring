package capstone.capstone.repository;

import capstone.capstone.domain.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {
    @Query(value="select * from Chatting ch where ch.cht_room_num = :cht_room_num order by ch.cht_time", nativeQuery = true)
    List<Chatting> enterChattingRoom(@Param("cht_room_num")int cht_room_num);

    @Query(value="select * from (select ch.cht_text from chatting ch where ch.cht_room_num = :cht_room_num order by ch.cht_time desc) where rownum <= 1", nativeQuery = true)
    String getLastMsg(@Param("cht_room_num") int cht_room_num);

    @Query(value="select * from (select ch.cht_time from chatting ch where ch.cht_room_num = :cht_room_num order by ch.cht_time desc) where rownum <= 1", nativeQuery = true)
    LocalDateTime getLastTime(@Param("cht_room_num") int cht_room_num);
}
