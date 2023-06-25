package capstone.capstone.repository;

import capstone.capstone.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Integer> {
    @Query(value="SELECT * FROM ChattingRoom WHERE ChattingRoom.guest_member = :guest_num OR ChattingRoom.host_member = :guest_num ORDER BY ChattingRoom.cht_room_num", nativeQuery = true)
    List<ChattingRoom> getAllChattingRoom(@Param("guest_num") int guest_num);

    @Query(value="SELECT * FROM ChattingRoom chr WHERE chr.guest_member = :guest_num AND chr.post_num = :post_num", nativeQuery = true)
    List<ChattingRoom> enterChattingRoom(@Param("post_num")Integer post_num, @Param("guest_num")Integer guest_num);

    @Query(value = "SELECT p.post_title FROM Post p WHERE p.post_num = (SELECT chr.post_num FROM ChattingRoom chr WHERE chr.cht_room_num = :cht_room_num)", nativeQuery = true)
    String getChattingPostTitle(@Param("cht_room_num")Integer cht_room_num);

    @Query(value = "SELECT p.picture_location FROM Picture p WHERE p.post_num = (SELECT chr.post_num FROM ChattingRoom chr WHERE chr.cht_room_num = :cht_room_num)", nativeQuery = true)
    String getChattingPostPicture(@Param("cht_room_num")Integer cht_room_num);

    @Query(value = "SELECT chr.host_member FROM ChattingRoom chr WHERE chr.cht_room_num = :cht_room_num", nativeQuery = true)
    Integer getHostInfo(@Param("cht_room_num")Integer cht_room_num);

    @Query(value = "SELECT chr.guest_member FROM ChattingRoom chr WHERE chr.cht_room_num = :cht_room_num", nativeQuery = true)
    Integer getGuestInfo(@Param("cht_room_num")Integer cht_room_num);
}
