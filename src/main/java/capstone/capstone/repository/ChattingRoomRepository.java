package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {

    @Query(value="select * from ChattingRoom where ChattingRoom.guest_member = :guest_no order by ChattingRoom.cht_room_num", nativeQuery = true)
    List<ChattingRoom> findAllId(@Param("guest_no") int guest_no);

    @Query(value="select * from ChattingRoom chr where chr.guest_member = :guest_no AND chr.post_no = :post_no", nativeQuery = true)
    List<ChattingRoom> findByOne(@Param("post_no")Integer post_no, @Param("guest_no")Integer guest_no);

    @Query(value = "select p.post_title from POST p where p.post_no = (select chr.post_no from CHATTINGROOM chr where chr.cht_room_num = :cht_room_no)", nativeQuery = true)
    String getPostTitle(@Param("cht_room_no")Integer cht_room_no);

    @Query(value = "select chr.host_member from CHATTINGROOM chr where chr.cht_room_num = :cht_room_no", nativeQuery = true)
    Integer getHostInfo(@Param("cht_room_no")Integer cht_room_no);

    @Query(value = "select chr.guest_member from CHATTINGROOM chr where chr.cht_room_num = :cht_room_no", nativeQuery = true)
    Integer getGuestInfo(@Param("cht_room_no")Integer cht_room_no);
}
