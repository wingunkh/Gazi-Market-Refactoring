package capstone.capstone.service;

import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Post;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChattingRoomService {
    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

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

    public Integer enterChattingRoom(Integer postNum, Integer guestNum) {
        List<ChattingRoom> chattingRoom = chattingRoomRepository.enterChattingRoom(postNum, guestNum);
        ChattingRoom ch;

        if(chattingRoom.isEmpty()) {
            Optional<Post> optionalPost = postRepository.findById(postNum);

            if (optionalPost.isPresent()) {
                ch = new ChattingRoom(postNum, guestNum, optionalPost.get().getMember().getMemberNum());
                ch = createChattingRoom(ch);
            } else
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
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