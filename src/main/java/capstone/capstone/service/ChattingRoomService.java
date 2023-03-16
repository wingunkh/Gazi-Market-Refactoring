package capstone.capstone.service;

import capstone.capstone.domain.Chatting;
import capstone.capstone.domain.ChattingRoom;
import capstone.capstone.domain.Picture;
import capstone.capstone.domain.Posts;
import capstone.capstone.exception.ResourceNotFoundException;
import capstone.capstone.repository.ChattingRepository;
import capstone.capstone.repository.ChattingRoomRepository;
import capstone.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChattingRoomService {
    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private PostRepository postRepository;

    public List<ChattingRoom> getAllChattingRoom(int guest_no) {
        return chattingRoomRepository.findAllId(guest_no);
    }

    public ChattingRoom createChattingRoom(ChattingRoom chattingRoom){
        return chattingRoomRepository.save(chattingRoom);
    }

    public List<Chatting> getChattingRoom(Integer post_no, Integer guest_no) {
        ChattingRoom chattingRoom = chattingRoomRepository.findByOne(post_no, guest_no);
        if(chattingRoom == null){
            chattingRoom.setPost_no(post_no);
            chattingRoom.setGuest_member(guest_no);
            chattingRoom.setHost_member(postRepository.findHost(post_no));
            chattingRoom = createChattingRoom(chattingRoom);
        }

        List<Chatting> chatting = chattingRepository.findAllDate(chattingRoom.getCht_room_num());

        return chatting;
    }
}
