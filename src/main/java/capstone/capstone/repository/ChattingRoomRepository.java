package capstone.capstone.repository;

import capstone.capstone.domain.Categories;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {

    @Query(value="select * from ChattingRoom where ChattingRoom.guest_member == guest_no order by ChaattingRoom", nativeQuery = true)
    List<ChattingRoom> findAllId(int guest_no);

    @Query(value="select * from ChattingRoom chr where chr.guest_member == guest_no && chr.post_no == post_no order by ChaattingRoom", nativeQuery = true)
    ChattingRoom findByOne(int post_no, int guest_no);
}
