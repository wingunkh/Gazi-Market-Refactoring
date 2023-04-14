package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {

    @Query(value="select * from ChattingRoom where ChattingRoom.guest_member = :guest_num order by ChattingRoom.cht_room_num", nativeQuery = true)
    List<ChattingRoom> findAllId(@Param("guest_num") int guest_num);

    @Query(value="select * from ChattingRoom chr where chr.guest_member = :guest_num AND chr.post_num = :post_num", nativeQuery = true)
    List<ChattingRoom> findByOne(@Param("post_num")Integer post_num, @Param("guest_num")Integer guest_num);

    @Query(value = "select p.post_title from POST p where p.post_num = (select chr.post_num from CHATTINGROOM chr where chr.cht_room_num = :cht_room_num)", nativeQuery = true)
    String getPostTitle(@Param("cht_room_num")Integer cht_room_num);

    @Query(value = "select chr.host_member from CHATTINGROOM chr where chr.cht_room_num = :cht_room_num", nativeQuery = true)
    Integer getHostInfo(@Param("cht_room_num")Integer cht_room_num);

    @Query(value = "select chr.guest_member from CHATTINGROOM chr where chr.cht_room_num = :cht_room_num", nativeQuery = true)
    Integer getGuestInfo(@Param("cht_room_num")Integer cht_room_no);
}
