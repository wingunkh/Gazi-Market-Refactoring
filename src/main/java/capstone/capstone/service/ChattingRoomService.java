package capstone.capstone.service;

import capstone.capstone.domain.Chatting;
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

    public List<ChattingRoom> getguestAllChattingRoom(int guest_no) {
        return chattingRoomRepository.findAllId(guest_no);
    }

    public List<ChattingRoom> getAllChattingRoom(){
        return chattingRoomRepository.findAll();
    }

    public ChattingRoom createChattingRoom(ChattingRoom chattingRoom){
        return chattingRoomRepository.save(chattingRoom);
    }

    public String getChattingPostTitle(int cht_room_no){
        return chattingRoomRepository.getPostTitle(cht_room_no);
    }

    public int getHostInfo(int cht_room_no){
        return chattingRoomRepository.getHostInfo(cht_room_no);
    }

    public int getGuestInfo(int cht_room_no){
        return chattingRoomRepository.getGuestInfo(cht_room_no);
    }

    public List<Chatting> getChattingRoom(Integer post_no, Integer guest_no) {
        List<ChattingRoom> chattingRoom = chattingRoomRepository.findByOne(post_no, guest_no);
        ChattingRoom ch;
        if(chattingRoom == null){
            ch = new ChattingRoom();
            ch.setPost_no(post_no);
            ch.setGuest_member(guest_no);
            ch.setHost_member(postRepository.findHost(post_no));
            ch = createChattingRoom(ch);
        }else{
            ch = chattingRoom.get(0);
        }

        List<Chatting> chatting = chattingRepository.findAllDate(ch.getCht_room_num());

        return chatting;
    }
}
