package capstone.capstone.repository;

import capstone.capstone.domain.Categories;
import capstone.capstone.domain.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {

    @Query(value="select * from Chatting ch where ch.cht_room_num == cht_room_no order by ch.cht_time", nativeQuery = true)
    List<Chatting> findAllDate(int cht_room_no);


    @Query(value="select * from (select ch.cht_text from chatting ch where ch.cht_room_num == cht_room_no order by ch.cht_time) where rownum <= 1", nativeQuery = true)
    String findlastmsg(int cht_room_no);
}
