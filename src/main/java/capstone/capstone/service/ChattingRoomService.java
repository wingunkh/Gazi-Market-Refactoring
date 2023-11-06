package capstone.capstone.service;

import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.repository.ChattingRepository;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChattingRoomService {
    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private PostRepository postRepository;

    public ChattingRoom createChattingRoom(ChattingRoom chattingRoom){
        return chattingRoomRepository.save(chattingRoom);
    }

    public List<ChattingRoom> getAllChattingRoom(int guest_num) {
        return chattingRoomRepository.getAllChattingRoom(guest_num);
    }

    public List<ChattingRoom> getAllChattingRoom(){
        return chattingRoomRepository.findAll();
    }

    public int enterChattingRoom(Integer post_num, Integer guest_num) {
        List<ChattingRoom> chattingRoom = chattingRoomRepository.enterChattingRoom(post_num, guest_num);
        ChattingRoom ch;
        if(chattingRoom.size() == 0){
            ch = new ChattingRoom(post_num, guest_num, postRepository.findHost(post_num));
            ch = createChattingRoom(ch);
        } else{
            ch = chattingRoom.get(0);
        }

        return ch.getRoomNum();
    }

    public String getChattingPostTitle(int cht_room_num){
        return chattingRoomRepository.getChattingPostTitle(cht_room_num);
    }

    public String getChattingPostPicture(int cht_room_num) {
        return chattingRoomRepository.getChattingPostPicture(cht_room_num);
    }

    public int getHostInfo(int cht_room_num){
        return chattingRoomRepository.getHostInfo(cht_room_num);
    }

    public int getGuestInfo(int cht_room_num){
        return chattingRoomRepository.getGuestInfo(cht_room_num);
    }
}