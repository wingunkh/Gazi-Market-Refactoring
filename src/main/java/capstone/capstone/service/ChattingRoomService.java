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

    public List<ChattingRoom> getguestAllChattingRoom(int guest_num) {
        return chattingRoomRepository.findAllId(guest_num);
    }

    public List<ChattingRoom> getAllChattingRoom(){
        return chattingRoomRepository.findAll();
    }

    public ChattingRoom createChattingRoom(ChattingRoom chattingRoom){
        return chattingRoomRepository.save(chattingRoom);
    }

    public String getChattingPostTitle(int cht_room_num){
        return chattingRoomRepository.getPostTitle(cht_room_num);
    }

    public int getHostInfo(int cht_room_num){
        return chattingRoomRepository.getHostInfo(cht_room_num);
    }

    public int getGuestInfo(int cht_room_num){
        return chattingRoomRepository.getGuestInfo(cht_room_num);
    }

    public List<Chatting> getChattingRoom(Integer post_num, Integer guest_num) {
        List<ChattingRoom> chattingRoom = chattingRoomRepository.findByOne(post_num, guest_num);
        ChattingRoom ch;
        if(chattingRoom == null){
            ch = new ChattingRoom();
            ch.setPost_num(post_num);
            ch.setGuest_member(guest_num);
            ch.setHost_member(postRepository.findHost(post_num));
            ch = createChattingRoom(ch);
        }else{
            ch = chattingRoom.get(0);
        }

        List<Chatting> chatting = chattingRepository.findAllDate(ch.getCht_room_num());

        return chatting;
    }
}
